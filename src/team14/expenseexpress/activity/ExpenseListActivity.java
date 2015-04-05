package team14.expenseexpress.activity;


import java.util.ArrayList;

import team14.expenseexpress.ExpenseListAdapter;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.model.Expense;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseListActivity extends Activity {
	
	double CAD = 0, USD = 0, EUR = 0, GBP = 0, CHF = 0, JPY = 0, CNY = 0;
	double number;
	private ArrayList<String> amountListString;
	private ExpenseListAdapter expenseListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claims_expense_list);
		
		ExpenseController.initialize();
		
		TextView claimNameView = (TextView) findViewById(R.id.claimNameTitles);
		ListView expenseListView = (ListView) findViewById(R.id.ExpenseList); 

		claimNameView.setText(ClaimController.getInstance().getSelectedClaim().getName());
		expenseListAdapter = new ExpenseListAdapter(this, ExpenseController.getInstance().getExpenseList().getExpenses());
		expenseListView.setAdapter(expenseListAdapter);
		expenseListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(ExpenseListActivity.this);
				adb.setMessage("Menu Of "+ ExpenseController.getInstance().getExpenseList().get(position).getName());
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							ExpenseController.getInstance().removeExpense
							(ExpenseController.getInstance().getExpenseList().get(finalPosition));
							expenseListAdapter.notifyDataSetChanged();
							
						}										
					});
				adb.setNeutralButton("Edit", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ExpenseController.getInstance().setSelectedExpense(finalPosition);
						startActivity(new Intent(ExpenseListActivity.this, ExpenseEditActivity.class));
						
					}										
				});
				adb.setNegativeButton("Details", new OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ExpenseController.getInstance().setSelectedExpense(finalPosition);
						startActivity(new Intent(ExpenseListActivity.this, ExpenseDetailsActivity.class));
					}
				});
				//Toast.makeText(ListStudentsActivity.this, "Is the on click working?", Toast.LENGTH_SHORT).show();
				adb.show();
				return false;
			}			
		});
	}
	
	public void totalCost() {
		amountListString = new ArrayList<String>();
		ArrayList<Expense> amountlist = ClaimController.getInstance().getSelectedClaim().getExpenseList().getExpenses();
		if (amountlist.size() != 0) {
			for (int i = 0; i < amountlist.size(); i++) {
				number = amountlist.get(i).getAmount().getNumber();
				String currency = amountlist.get(i).getAmount().getCurrency().getName();
				if(currency.equals("CAD")) {
					CAD = CAD + number;
				} else if(currency.equals("USD")) {
					USD = USD + number;
				} else if(currency.equals("EUR")) {
					EUR = EUR + number;
				} else if(currency.equals("GBP")) {
					GBP = GBP + number;
				} else if(currency.equals("CHF")) {
					CHF = CHF + number;
				} else if(currency.equals("JPY")) {
					JPY = JPY + number;
				} else if(currency.equals("CNY")) {
					CNY = CNY + number;
				}
			}
		}
		determineCosts();
		ClaimController.getInstance().getSelectedClaim().setTotalAmounts(amountListString);
	}
	public void determineCosts() {
		if(CAD != 0) {
			String line = "CAD" + " " + CAD;
			amountListString.add(line);
		}
		if(USD != 0) {
			String line = "USD" + " " + USD;
			amountListString.add(line);
		}
		if(EUR != 0) {
			String line = "EUR" + " " + EUR;
			amountListString.add(line);
		}
		if(GBP != 0) {
			String line = "GBP" + " " + GBP;
			amountListString.add(line);
		}
		if(CHF != 0) {
			String line = "CHF" + " " + CHF;
			amountListString.add(line);
		}
		if(JPY != 0) {
			String line = "JPY" + " " + JPY;
			amountListString.add(line);
		}
		if(CNY != 0) {
			String line = "CNY" + " " + CNY;
			amountListString.add(line);
		}
	}
	@Override
	protected void onResume(){
		super.onResume();
		expenseListAdapter.notifyDataSetChanged();
		totalCost();
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
	
    public void onClick_NewExpense(View v) {
    	ExpenseController.getInstance().makeSelectedExpense();
    	Toast.makeText(this,String.valueOf(ExpenseController.getInstance().getSelectedExpense().getId()), Toast.LENGTH_LONG).show();
    	startActivity(new Intent(ExpenseListActivity.this, ExpenseEditActivity.class));
    	
    }
	
}
