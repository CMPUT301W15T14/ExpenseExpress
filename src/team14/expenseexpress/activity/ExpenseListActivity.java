package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.R.layout;
import team14.expenseexpress.R.menu;
import team14.expenseexpress.controller.ClaimListController;
import team14.expenseexpress.model.Claim;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ExpenseListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claims_expense_list);
		Claim claim = ClaimListController.getSelectedClaim();
		TextView claimNameView = (TextView) findViewById(R.id.claimNameTitles);//get name view
		ListView expenseListView = (ListView) findViewById(R.id.ExpenseList); //get listview
		
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
