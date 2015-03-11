package team14.expenseexpress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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
        app.getMode();
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
    
    public void addClaim(View v) {
    	// startActivity(new Intent(ClaimListActivity.this, addClaimActivity.class);
    }
    
    public void showTags(View v) {

    	// startActivity(new Intent(ClaimListActivity.this, TagListActivity.class);
    }

}
