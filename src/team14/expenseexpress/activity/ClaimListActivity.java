package team14.expenseexpress.activity;

import android.app.Activity;
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
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_list);
        claimTags = app.getTags();
        chosenTags = new ArrayList<ClaimTag>();
    }
    
    private class TagListDialogFragment extends DialogFragment{
    	// private since this is made to be used only in this activity
    	// not static since it directly references claimTags and chosenTags
    	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.activity_tags, container, false);
			((ListView) findViewById(R.id.currentTagsList)).setAdapter(new TagsListAdapter());
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
				final ClaimTag tag = claimTags.get(position);
				
				CheckBox v = (CheckBox) convertView;
				if (v==null){
					v = new CheckBox(ClaimListActivity.this);
				}
				
				if (chosenTags.contains(tag)){
					v.setChecked(true);
				}
				
				v.setText(tag.getName());
				
				v.setOnClickListener(new OnClickListener() {
					
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
				
				return v;
			}
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
