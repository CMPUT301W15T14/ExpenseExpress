/*package team14.expenseexpress.activity;

import java.util.ArrayList;
import java.util.Calendar;


import team14.expenseexpress.R;
<<<<<<< HEAD
import team14.expenseexpress.controller.ExpenseListController;
=======
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.ExpenseListController;
import team14.expenseexpress.model.Expense;
>>>>>>> 004a966e77c6dea28754465f202b1d4b976f2ea0
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseEditActivity extends Activity {

	private Expense currentExpense;
	
	private EditText expenseName;
	private EditText expenseDescription;
	
	private int expenseYear, expenseMonth, expenseDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_edit);
		ExpenseController.getInstance(this.);
		
		/*
		setContentView(R.layout.activity_expense_edit);
		ExpenseListManager.initManager(this.getApplicationContext());
		
		Intent intent = getIntent();
		expensePosition = intent.getIntExtra("expensePosition", -1);
		expenseId = intent.getLongExtra("claimId", -1);
		
		initExpenseList();
		
		expenseDateView = (TextView) findViewById(R.id.expenseDateText);
		editDescriptionText = (EditText) findViewById(R.id.editExpenseDescription);
		amountText = (EditText) findViewById(R.id.editAmountText);
		currencyText = (EditText) findViewById(R.id.editCurrencyText);
		
		if(expensePosition >= 0) {
			currentExpense = expenseList.get(expensePosition);
			expenseDateView.setText(currentExpense.expenseDateToString());
			editDescriptionText.append(currentExpense.getDescription());
			amountText.append(Double.toString(currentExpense.getAmount()));
			currencyText.append(currentExpense.getCurrency());
		} if(expenseId >= 0) {
			Toast.makeText(this, "New Expense", Toast.LENGTH_SHORT).show();
			currentExpense = new Expense(expenseId);
		}
		
		Calendar calendar = currentExpense.getExpenseDate();
		expenseYear = calendar.get(Calendar.YEAR);
		expenseMonth = calendar.get(Calendar.MONTH);
		expenseDay = calendar.get(Calendar.DAY_OF_MONTH);
		showDate();
		
		*/
//	}
	//*******************************************************************************
	//http://www.tutorialspoint.com/android/android_datepicker_control.htm   
	//All DatePicker code was implemented from this source, accessed Feb.1,2015
/*	@SuppressWarnings("deprecation")
	public void setExpenseDate(View view) {
		showDialog(777);
		Toast.makeText(getApplicationContext(), "Expense Date", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == 777) {
			return new DatePickerDialog(this, expenseDateListener, expenseYear, expenseMonth, expenseDay);
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener expenseDateListener
	= new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int year, int month, int day) {
			expenseYear = year;
			expenseMonth = month;
			expenseDay=day;
			Calendar date = Calendar.getInstance();
			date.set(year,month,day);
			currentExpense.setExpenseDate(date);
			showDate();
		}
	};

	private void showDate() {
		expenseDateView.setText(new StringBuilder().append(expenseMonth+1).append("-").append(expenseDay).append("-").append(expenseYear));
   }
	//*******************************************************************************
	
	private void initExpenseList() {
		expenseList = new ArrayList<Expense>(ExpenseListController.getExpenseList().getExpenses());
		subExpenseList = new ArrayList<Expense>();
		for(int i = 0; i<=expenseList.size()-1; i++) {
			if(expenseList.get(i).getExpenseId() == expenseId) {
				subExpenseList.add(expenseList.get(i));
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_edit, menu);
		return true;
	}
	
	public void deleteExpense(MenuItem menu) {
		Toast.makeText(this, "Expense Deleted", Toast.LENGTH_SHORT).show();
		if(expensePosition >= 0) {
			ExpenseListController.getExpenseList().removeExpense(currentExpense);
		}
		finish();
	}
	
	public void saveExpense(MenuItem menu) {
		Toast.makeText(this, "Expense Saved", Toast.LENGTH_SHORT).show();
		ExpenseListController controller = new ExpenseListController();
		currentExpense.setDescription(editDescriptionText.getEditableText().toString());
		try {
			currentExpense.setAmount(Double.parseDouble(amountText.getEditableText().toString()));
		} catch(NumberFormatException e) {
			currentExpense.setAmount(0);
			Toast.makeText(this, "No Amount Declared", Toast.LENGTH_SHORT).show();
		}
		currentExpense.setCurrency(currencyText.getEditableText().toString());
		if(expensePosition <0) {
			controller.addExpense(currentExpense);
		} else {
			ExpenseListController.getExpenseList().updateExpense();
		}
		finish();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Toast.makeText(this, "Expense Updated", Toast.LENGTH_SHORT).show();
		ExpenseListController controller = new ExpenseListController();
		currentExpense.setDescription(editDescriptionText.getEditableText().toString());
		try {
			currentExpense.setAmount(Double.parseDouble(amountText.getEditableText().toString()));
		} catch(NumberFormatException e) {
			currentExpense.setAmount(0);
			Toast.makeText(this, "No Amount Declared", Toast.LENGTH_SHORT).show();
		}
		currentExpense.setCurrency(currencyText.getEditableText().toString());
		if(expensePosition <0) {
			controller.addExpense(currentExpense);
		} else {
			ExpenseListController.getExpenseList().updateExpense();
		}
	}
}
	
}
*/
