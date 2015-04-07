package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.ApproverAdapter;
import team14.expenseexpress.ClaimListAdapter;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.HomeGeo;
import team14.expenseexpress.R;
import team14.expenseexpress.activity.TagListDialogFragment.TagsListAdapter;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.TagListController;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.Receipt;
import team14.expenseexpress.model.Status;
import team14.expenseexpress.util.ElasticSearchHelper;
import team14.expenseexpress.util.LocalFileHelper;
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
import android.widget.TextView;
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
	private ClaimListAdapter claimsListAdapter;
	private ApproverAdapter approverAdapter;
	public static boolean edit = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_list);
    	ClaimController.getInstance().initialize(this);
    	LayoutInflater.from(this);
    	if(Mode.get() == Mode.APPROVER) {
    		final ListView approverView = (ListView) findViewById(R.id.claimListView);
    		approverAdapter = new ApproverAdapter(this, ClaimController.getInstance().getClaimList().getClaims());
    		approverView.setAdapter(approverAdapter);
    		approverAdapter.getSubmittedClaims();
    	} else {
	    	TagListController.getInstance().initialize();
			initializeListViewClaimList();
    	}
		setSubtitle();
		displayUiBasedOnMode();
	}
	
	public void addHomeGeo(View v){
		Intent intent = new Intent(ClaimListActivity.this,
				HomeGeo.class);
		startActivity(intent);
	}
	/**
	 * Hides UI that's irrelevant to the mode chosen by the user
	 */
	private void displayUiBasedOnMode() {
		View claimantUI = findViewById(R.id.linearLayout_claimantOnlyUserInterface);
		switch (Mode.get()){
		case Mode.APPROVER:
			hide(claimantUI);
			break;
		case Mode.CLAIMANT:
			// TODO: add approverUI. here, hide(approverUI);
			break;
		}
	}

	/**
	 * Displays the user's name and chosen mode in a subtitle
	 */
	private void setSubtitle() {
		TextView textView_usernameAndMode = (TextView) findViewById(R.id.textView_usernameAndMode);
		String subtitle = UserController.getInstance().getCurrentUser().getName();
		switch(team14.expenseexpress.controller.Mode.get()){
		case Mode.APPROVER:
			subtitle += " - Approver";
			break;
		case Mode.CLAIMANT:
			subtitle += " - Claimant";
			break;
		case Mode.OFFLINE:
			subtitle += " - Offline";
		}
		textView_usernameAndMode.setText(subtitle);
	}

	/**
	 * Loads the claim list into the ListView
	 */
	private void initializeListViewClaimList() {
		final ListView listView_claimList = (ListView) findViewById(R.id.claimListView);
		claimsListAdapter = new ClaimListAdapter(this);
		setClaimListAdapter(claimsListAdapter);
		listView_claimList.setAdapter(claimsListAdapter);
		registerForContextMenu(listView_claimList);
		listView_claimList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Claim claim = (Claim) listView_claimList.getItemAtPosition(position);
				ClaimController.getInstance().setSelectedClaim(claim);
				Intent intent = new Intent(ClaimListActivity.this,
						ExpenseListActivity.class);
				startActivity(intent);
			}
		});
	}
	
	/**
	 * Shows all claims.
	 */
	@Override
	protected void onResume(){
		super.onResume();
		if(Mode.get() == Mode.APPROVER) {
			//approverUpdate();
		} else {
			claimsListAdapter.updateFilteredClaimList(new ArrayList<ClaimTag>());
		}
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
			view.findViewById(R.id.addTagButton).setOnClickListener(
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
								TagListController.getInstance().addTag(new ClaimTag(text));
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
		String[] menuItems = null;
		switch(Mode.get()) {
		case(Mode.APPROVER) :
			menuItems = getResources().getStringArray(R.array.LongClickMenuApprov);
			break;
		case(Mode.CLAIMANT) :
			menuItems = getResources().getStringArray(R.array.LongClickMenu);
			break;
		}
		for (int i = 0; i < menuItems.length; i++) {
			menu.add(Menu.NONE, i, i, menuItems[i]);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		String[] menuItems = null;
		final ListView lv1 = (ListView) findViewById(R.id.claimListView);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		menuItems = getResources().getStringArray(R.array.LongClickMenu);

		String menuItemName = menuItems[menuItemIndex];

		Claim claim = (Claim) lv1.getItemAtPosition(info.position);

		if (menuItemName.equals("Delete")) {
			toast(String.valueOf(claim.getId()));
			ClaimController.getInstance().removeClaim(claim);
			claimsListAdapter.updateFilteredClaimList(TagListController.getInstance().getChosenTags().getTags());
		} else if (menuItemName.equals("Edit")) {
			if (claim.getStatus().equals(Status.SUBMITTED)
					|| (claim.getStatus().equals(Status.APPROVED))) {
				Toast.makeText(this, "Cannot Edit Claim", Toast.LENGTH_SHORT).show();
			} else {
				edit = true;
				ClaimController.getInstance().setSelectedClaim(claim);
				startActivity(new Intent(ClaimListActivity.this, ClaimEditActivity.class));
			}
		} else if (menuItemName.equals("Details")) {
			ClaimController.getInstance().setSelectedClaim(claim);
			startActivity(new Intent(ClaimListActivity.this, ClaimDetailsActivity.class));
		} else if (menuItemName.equals("Submit")) {
			if (claim.getStatus().equals(Status.SUBMITTED)) {
				toast("Claim already Submitted");
			} else {
				boolean allcomplete = true;
				for (Expense expense : claim.getExpenseList().getExpenses()){
					if (expense.getIncomplete()){
						allcomplete = false;
					}
				}
				if (allcomplete){
					claim.setStatus(Status.SUBMITTED);
					ElasticSearchHelper.getInstance().addClaim(claim);
							
					
				} else { 
					Toast.makeText(this, "Can not submit incomplete expenses", Toast.LENGTH_LONG).show();
				}
				claimsListAdapter.updateFilteredClaimList(TagListController.getInstance().getChosenTags().getTags());
				LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
			}
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
	public void setClaimListAdapter(ClaimListAdapter adapter) {
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
		ClaimController.getInstance().setSelectedClaim(new Claim());
		ClaimController.getInstance().addClaim(ClaimController.getInstance().getSelectedClaim());
		startActivity(new Intent(ClaimListActivity.this, ClaimEditActivity.class));
	}


	/**
	 * Communication method for updating the BaseAdapter
	 * 
	 * @param tags Chosen tags
	 */
	public void filterClaimsByTags(ArrayList<ClaimTag> tags) {
		claimsListAdapter.updateFilteredClaimList(tags);
		setChosenTagsTextView();
	}

	/**
	 * Updates the Chosen Tags text appropriately.
	 */
	private void setChosenTagsTextView() {
		String tagsString = "";
		ArrayList<ClaimTag> chosenTags = TagListController.getInstance().getChosenTags().getTags();
		if (chosenTags.size()>0){
			for (int i = 0; i < chosenTags.size(); i++){
				tagsString += chosenTags.get(i).getName();
				tagsString += ", ";
			}
			tagsString = tagsString.substring(0,tagsString.length()-2);
		} else {
			tagsString = "(Showing all Claims)";
		}
		((TextView)findViewById(R.id.textView_chosenTags)).setText(tagsString);
	}

	public void approverUpdate() {
		this.approverAdapter.notifyDataSetChanged();
	}

	public void onItemClick(Claim claim) {
		ClaimController.getInstance().setSelectedClaim(claim);
		startActivity(new Intent(this, ExpenseListActivity.class));
		
	}
}
