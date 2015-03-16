package team14.expenseexpress.activity;

import team14.expenseexpress.CustomBaseAdapter;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.activity.TagListDialogFragment.TagsListAdapter;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.TagListController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
/**
 * <p> View
 * <p> ClaimListActivity Class:
 * This view is for showing the list of claims a user has.
 * It allows the user to see a list of claims, go to the add a new claim activity, 
 * see a list of tags and add new tags. The user will also be able to sort claims
 * by tags.
 */
public class ClaimListActivity extends ExpenseExpressActivity {

	private TagsListAdapter tagsListAdapter;
	private CustomBaseAdapter claimsListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_list);
    	ClaimController.getInstance().initialize(this);
    	TagListController.getInstance().initialize();
	    
		LayoutInflater.from(this);

		final ListView lv1 = (ListView) findViewById(R.id.claimListView);
		CustomBaseAdapter adapter = new CustomBaseAdapter(this, ClaimController.getInstance().getClaimList().getClaims());
		setClaimListAdapter(adapter);
		lv1.setAdapter(adapter);
		registerForContextMenu(lv1);
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Claim claim = (Claim) lv1.getItemAtPosition(position);
				ClaimController.getInstance().setSelectedClaim(claim);
				Intent intent = new Intent(ClaimListActivity.this,
						ExpenseListActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		claimsListAdapter.notifyDataSetChanged();
	}
	
	/**
	 * A DialogFragment class for tags. This creates a dialog that will show a list tags
	 * and allows the user to add new tags. 
	 * <p> Constraints: No duplicate tags, name field must be filled in
	 */
	@SuppressLint("ValidFragment")
	private class NewTagDialogFragment extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Dialog dialog = super.onCreateDialog(savedInstanceState);

			// request a window without the title
			dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			return dialog;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View view = inflater.inflate(R.layout.activity_add_tag,
					container, false);
			view.findViewById(R.id.newTagButton).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							String text = ((EditText) view
									.findViewById(R.id.newTagNameField))
									.getText().toString();
							if (text.length() == 0) {
								toast("Tag name can't be empty");
							} else if (TagListController.getInstance().getTagList().getTags().contains(new ClaimTag(text))) {
									toast("Already in list");
									return;
								}
								// if all good, add to list and dismiss dialog
								TagListController.getInstance().getTagList().getTags().add(new ClaimTag(text));
								toast("Added to list");
								updateTagsListAdapter();
								dismiss();
							}
					});
			return view;
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		String[] menuItems = getResources().getStringArray(
				R.array.LongClickMenu);
		for (int i = 0; i < menuItems.length; i++) {
			menu.add(Menu.NONE, i, i, menuItems[i]);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		final ListView lv1 = (ListView) findViewById(R.id.claimListView);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(
				R.array.LongClickMenu);
		String menuItemName = menuItems[menuItemIndex];

		Claim claim = (Claim) lv1.getItemAtPosition(info.position);

		if (menuItemName.equals("Delete")) {
			ClaimController.getInstance().removeClaim(claim);
			claimsListAdapter.notifyDataSetChanged();
		} else if (menuItemName.equals("Edit")) {
			if (claim.getStatus().equals("submitted")
					|| (claim.getStatus().equals("approved"))) {
				Toast.makeText(this, "Cannot Edit Claim", Toast.LENGTH_SHORT)
						.show();
			} else {
				//startActivity(new Intent(ClaimListActivity.this,ClaimDetailsAddEditActivity.class));
			}
		} else if (menuItemName.equals("Details")) {
			//startActivity(new Intent(ClaimListActivity.this,
			//		ClaimDetailsActivity.class));
		}
		return true;
	}
	
	/**
	 * A method to set the adapter for tags in order to notifyDataSetChanged() 
	 * @param adapter The TagsListAdapter used
	 */
	public void setTagsListAdapter(TagsListAdapter adapter) {
		this.tagsListAdapter = adapter;
	}
	
	/**
	 * A method to set the adapter for claims in order to notifyDataSetChanged() 
	 * @param adapter The CustomBaseAdapter used
	 */
	public void setClaimListAdapter(CustomBaseAdapter adapter) {
		this.claimsListAdapter = adapter;
	}
	
	/**
	 * A method to update the tag list when a new tag is added by calling notifyDataSetChanged()
	 */
	private void updateTagsListAdapter() {
		tagsListAdapter.notifyDataSetChanged();
	}
	
	/**
	 * A method that calls the NewTagDialogFragment in order to create a dialog for adding a new tag
	 */
	public void showNewTagDialog() {
		FragmentManager fm = getFragmentManager();
		new NewTagDialogFragment().show(fm, "newTagDialogFragment");
	}

	/**
	 * when user press the "add new tag" button, this method calls TagListDialogFragment in order to
	 * create a dialog to view the list of tags
	 * @param v View
	 */
	public void onClick_seeTags(View v) {
		FragmentManager fm = getFragmentManager();
		new TagListDialogFragment(this).show(fm, "tagsListDialogFragment");

	}

	/**
	 * When user press the "add new claim" button goes to the NewClaimActivity
	 * @param v View
	 */
	public void onClick_newClaim(View v) {
		// TODO
		startActivity(new Intent(ClaimListActivity.this, NewClaimActivity.class));
	}

	public void onClick_searchByTag(View v) {
		// TODO
	}



}
