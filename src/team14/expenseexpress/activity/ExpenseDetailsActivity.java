package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.receipt.ViewReceiptActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ExpenseDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_details);
		// Show the Up button in the action bar.
		Expense expense = ExpenseController.getInstance().getSelectedExpense();
		
		TextView expenseNameTitle = (TextView) findViewById(R.id.expenseDetailsTitle);
		expenseNameTitle.setText(expense.getName());
		
		TextView expenseCategory = (TextView) findViewById(R.id.expenseDetailsCategory);
		expenseCategory.setText(expense.getCategory());
		
		TextView expenseDate = (TextView) findViewById(R.id.expenseDetailsDate);
		expenseDate.setText(android.text.format.DateFormat.format("yyyy-MM-dd",expense.getExpenseDate()));
		
		TextView expenseAmount = (TextView) findViewById(R.id.expenseDetailsAmount);
		expenseAmount.setText(Double.toString(expense.getAmount().getNumber()));
		
		TextView expenseCurrency = (TextView) findViewById(R.id.expenseDetailsCurrency);
		expenseCurrency.setText(expense.getAmount().getCurrency().getName());
		
		TextView expenseDescription = (TextView) findViewById(R.id.expenseDetailsDescription);
		expenseDescription.setText(expense.getDescription());
		
		TextView expenseComplete = (TextView) findViewById(R.id.expenseDetailsINC);
		expenseComplete.setText(Boolean.toString(expense.getIncomplete()));
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_details, menu);
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
	
	public void OnClick_ViewReceipt(View v){
		startActivity(new Intent(ExpenseDetailsActivity.this, ViewReceiptActivity.class));
	}
}
