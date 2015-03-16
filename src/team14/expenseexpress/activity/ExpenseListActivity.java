package team14.expenseexpress.activity;

import javax.xml.datatype.Duration;


import team14.expenseexpress.CustomBaseAdapter;
import team14.expenseexpress.ExpenseListAdapter;
import team14.expenseexpress.R;
import team14.expenseexpress.R.layout;
import team14.expenseexpress.R.menu;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.ExpenseList;
import team14.expenseexpress.model.Expense;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.support.v4.app.NavUtils;

public class ExpenseListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claims_expense_list);
		final Claim claim = ClaimController.getInstance().getSelectedClaim();
		TextView claimNameView = (TextView) findViewById(R.id.claimNameTitles);
		ListView expenseListView = (ListView) findViewById(R.id.ExpenseList); 
		claimNameView.setText(claim.getName());
		ExpenseListAdapter adapter = new ExpenseListAdapter(this, claim.getExpenseList());	
		expenseListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(ExpenseListActivity.this);
				final ExpenseList expenseList = claim.getExpenseList();
				adb.setMessage("Delete "+ expenseList.get(position).toString()+"?");
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Expense expense = expenseList.get(finalPosition);
							
							ClaimController.removeExpense(claim, expense);				//delete listener								
						}										
					});
				adb.setNeutralButton("Edit", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Expense expense = expenseList.get(finalPosition);
						ExpenseController EC = ExpenseController.getInstance();
						EC.setSelectedExpense(expense);
						startActivity(new Intent(ExpenseListActivity.this, ExpenseEditActivity.class));
						
					}										
				});
				adb.setNegativeButton("Cancel", new OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				//Toast.makeText(ListStudentsActivity.this, "Is the on click working?", Toast.LENGTH_SHORT).show();
				adb.show();
				return false;
			}			
		});
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
	
    public void onClick_newExpense(View v) {
    	startActivity(new Intent(ExpenseListActivity.this, ExpenseEditActivity.class));
    }
	
}
