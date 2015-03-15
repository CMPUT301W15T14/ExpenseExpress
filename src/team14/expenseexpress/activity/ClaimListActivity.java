package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.CustomBaseAdapter;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.activity.TagListDialogFragment.TagsListAdapter;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.TagList;
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

public class ClaimListActivity extends ExpenseExpressActivity {

	private ArrayList<ClaimTag> claimTags;
	private ArrayList<ClaimTag> chosenTags;
	private ArrayList<Claim> claimList;
	private ClaimController cListController;
	private CustomBaseAdapter adapter;
	private TagsListAdapter tagsListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_list);
		claimTags = TagList.getInstance(this).get();
		chosenTags = new ArrayList<ClaimTag>();
		LayoutInflater.from(this);

		// TODO
		cListController = ClaimController.getInstance();
		cListController.initialize(this);
		claimList = ClaimList.getInstance().getClaims();

		final ListView lv1 = (ListView) findViewById(R.id.claimListView);
		CustomBaseAdapter adapter = new CustomBaseAdapter(this, claimList);
		lv1.setAdapter(adapter);
		registerForContextMenu(lv1);
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Claim claim = (Claim) lv1.getItemAtPosition(position);
				cListController.setSelectedClaim(claim);
				Intent intent = new Intent(ClaimListActivity.this,
						ExpenseListActivity.class);
				startActivity(intent);
			}
		});
	}

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
							} else {
								ClaimTag tag = new ClaimTag(text);
								// check if it's already in list
								if (claimTags.contains(tag)) {
									toast("Already in list");
									return;
								}
								// if all good, add to list and dismiss dialog
								claimTags.add(tag);
								toast("Added to list");
								updateTagsListAdapter();
								dismiss();
							}
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
			claimList.remove(claim);
			log(adapter.toString());
			adapter.notifyDataSetChanged();
		} else if (menuItemName.equals("Edit")) {
			if (claim.getStatus().equals("submitted")
					|| (claim.getStatus().equals("approved"))) {
				Toast.makeText(this, "Cannot Edit Claim", Toast.LENGTH_SHORT)
						.show();
			} else {
				// startActivity(new Intent(ClaimListActivity.this,
				// ClaimDetailsAddEditActivity.class);
			}
		} else if (menuItemName.equals("Details")) {
			startActivity(new Intent(ClaimListActivity.this,
					ClaimDetailsActivity.class));
		}
		return true;
	}

	public void setTagsListAdapter(TagsListAdapter adapter) {
		this.tagsListAdapter = adapter;
	}

	private void updateTagsListAdapter() {
		tagsListAdapter.notifyDataSetChanged();
	}

	public void showNewTagDialog() {
		FragmentManager fm = getFragmentManager();
		new NewTagDialogFragment().show(fm, "newTagDialogFragment");
	}

	public void onClick_seeTags(View v) {
		FragmentManager fm = getFragmentManager();
		new TagListDialogFragment(this).show(fm, "tagsListDialogFragment");

	}

	public void onClick_newClaim(View v) {
		// TODO
		startActivity(new Intent(ClaimListActivity.this, NewClaimActivity.class));
	}

	public void onClick_searchByTag(View v) {
		// TODO
	}

	public ArrayList<ClaimTag> getChosenTags() {
		return chosenTags;
	}

}
