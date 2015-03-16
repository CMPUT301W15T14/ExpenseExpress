package team14.expenseexpress.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;


import team14.expenseexpress.R;
import team14.expenseexpress.controller.ExpenseController;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseEditActivity extends Activity {
	
	private GregorianCalendar date;
	
	private EditText expenseName;
	private EditText expenseDescription;
	private EditText expenseAmount;
	private TextView expenseDateView;
	
	private int expenseYear, expenseMonth, expenseDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_edit);
		
		expenseName = (EditText) findViewById(R.id.editExpenseName);
		expenseDescription = (EditText) findViewById(R.id.editDescription);
		expenseDateView = (TextView) findViewById(R.id.expenseDateView);
		expenseAmount = (EditText) findViewById(R.id.amountText);
		
		GregorianCalendar calendar = ExpenseController.getInstance().getExpenseDate();
		expenseYear = calendar.get(GregorianCalendar.YEAR);
		expenseMonth = calendar.get(GregorianCalendar.MONTH);
		expenseDay = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		showDate();
}

	//*******************************************************************************
	//http://www.tutorialspoint.com/android/android_datepicker_control.htm   
	//All DatePicker code was implemented from this source, accessed Feb.1,2015
	@SuppressWarnings("deprecation")
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
			date.set(year,month,day);
			expenseController.setExpenseDate((GregorianCalendar)date);
			showDate();
		}
	};

	private void showDate() {
		expenseDateView.setText(new StringBuilder().append(expenseMonth+1).append("-").append(expenseDay).append("-").append(expenseYear));
   }
	//*******************************************************************************
	
	
	public void modifyReceipt(View view) {
		Toast.makeText(this,"Feature Unavailable",Toast.LENGTH_SHORT).show();
	}
	
	/*
	public void saveExpense(View view) {
		Toast.makeText(this, "Expense Saved", Toast.LENGTH_SHORT).show();
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
	} */
}


