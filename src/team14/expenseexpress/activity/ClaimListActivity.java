package team14.expenseexpress.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import team14.expenseexpress.App;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.User;



public class ClaimListActivity extends ExpenseExpressActivity {

    private EditText editText_tagSearch;
    private ArrayList<ClaimTag> claimTags;
    private ArrayList<ClaimTag> chosenTags;
    private ArrayList<Claim> claims;
    private TagListDialogFragment.TagsListAdapter tagsListAdapter;
    private LayoutInflater inflater;
 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_list);
        claimTags = app.getTags();
        chosenTags = new ArrayList<ClaimTag>();
        inflater = LayoutInflater.from(this);
        
        // TODO 
    }
    
    @SuppressLint("ValidFragment")
	private class TagListDialogFragment extends DialogFragment{
    	
    	
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
			
			View v = inflater.inflate(R.layout.activity_tags, container, false);
			tagsListAdapter = new TagsListAdapter();
			(v.findViewById(R.id.addNewTagButton)).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View view) {
					showNewTagDialog();
				}
			});
			((ListView) v.findViewById(R.id.ExpenseList)).setAdapter(tagsListAdapter);
			return v;
		}
		


		private class TagsListAdapter extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return claimTags.size();
			}

			@Override
			public Object getItem(int arg0) {
				/* not needed */
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				/* not needed */
				return 0;
			}

			@Override
			public View getView(final int position, final View convertView, ViewGroup parent) {
				// Recycles convertView
				// TODO: put controller in between View and Model
				final ClaimTag tag = claimTags.get(position);
				
				CheckBox checkBox = (CheckBox) convertView;
				if (checkBox==null){
					checkBox = new CheckBox(ClaimListActivity.this);
				}
				
				if (chosenTags.contains(tag)){
					checkBox.setChecked(true);
				}
				
				checkBox.setText(tag.getName());
				
				checkBox.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// Is the view now checked?
					    boolean checked = ((CheckBox) v).isChecked();
			            if (checked){
			            	chosenTags.add(tag);
			            } else {
			            	chosenTags.remove(tag);
			            }
					}
				});
				
				return checkBox;
			}
		}
    	
    }
    
    private void lol(){
    new BaseAdapter(){

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			return null;
		}
    	
    };
    }
    
    @SuppressLint("ValidFragment")
	private class NewTagDialogFragment extends DialogFragment{
    	
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
			final View view = inflater.inflate(R.layout.activity_add_tag, container, false);
			view.findViewById(R.id.newTagButton).setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					String text = ((EditText) view.findViewById(R.id.newTagNameField)).getText().toString();
					if (text.length()==0){
						toast("Tag name can't be empty");
					} else {
						ClaimTag tag = new ClaimTag(text);
						// check if it's already in list
						if (claimTags.contains(tag)){
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
	public boolean onContextItemSelected(MenuItem item) {
		 
		 final ListView lv1 = (ListView) findViewById(R.id.claimListView);
		 AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		 int menuItemIndex = item.getItemId();
		 String[] menuItems = getResources().getStringArray(R.array.LongClickMenu);
		 String menuItemName = menuItems[menuItemIndex];
		 
		 Claim claim = (Claim) lv1.getItemAtPosition(info.position);
		 
		 if (menuItemName.equals("Delete")) {
			 claims.remove(claim);
			 finish();
			 startActivity(getIntent());
		 } else if (menuItemName.equals("Edit")) {
			 if (claim.getStatus().equals("submitted") || (claim.getStatus().equals("approved"))) {
				 Toast.makeText(this, "Cannot Edit Claim", Toast.LENGTH_SHORT).show();
			 } else {
				// startActivity(new Intent(ClaimListActivity.this, ClaimDetailsAddEditActivity.class);
			 }
		 }
		 return true;
    }
    
    private void updateTagsListAdapter(){
    	tagsListAdapter.notifyDataSetChanged();
    }
        
	private void showNewTagDialog(){
		FragmentManager fm = getFragmentManager();
        new NewTagDialogFragment().show(fm, "newTagDialogFragment");
	}
    
    public void onClick_seeTags(View v) {
    	FragmentManager fm = getFragmentManager();
        new TagListDialogFragment().show(fm, "tagsListDialogFragment");

    }
    
    public void onClick_newClaim(View v) {
    	// TODO
    }
    
    public void onClick_searchByTag(View v) {
    	// TODO
    }

}
