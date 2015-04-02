package team14.expenseexpress.activity;

import java.util.GregorianCalendar;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.receipt.ReceiptAddActivity;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseEditActivity extends Activity {
	
	private GregorianCalendar date;
	
	private EditText expenseName;
	private EditText expenseDescription;
	private EditText expenseAmount;
	private TextView expenseDateView;
	private Spinner ctgrySpinner;
	private Spinner crncySpinner ;
	private CheckBox completeCheck;
	private Button receiptButton;
	
	private int expenseYear, expenseMonth, expenseDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_edit);
		
		expenseName = (EditText) findViewById(R.id.editExpenseName);
		expenseDescription = (EditText) findViewById(R.id.editDescription);
		expenseDateView = (TextView) findViewById(R.id.expenseDateView);
		expenseAmount = (EditText) findViewById(R.id.amountText);
		ctgrySpinner = (Spinner) findViewById(R.id.categorySpinner);
		crncySpinner = (Spinner) findViewById(R.id.currencySpinner);
		completeCheck = (CheckBox) findViewById(R.id.completeCheck);
		receiptButton = (Button) findViewById(R.id.addReceipt);
		
		ArrayAdapter<CharSequence> ctgryAdapter = ArrayAdapter.createFromResource(this,
		        R.array.Categories, android.R.layout.simple_spinner_item);
		ctgryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ctgrySpinner.setAdapter(ctgryAdapter);
		ArrayAdapter<CharSequence> crncyAdapter = ArrayAdapter.createFromResource(this,
		        R.array.Currency, android.R.layout.simple_spinner_item);
		crncyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		crncySpinner.setAdapter(crncyAdapter);
		Expense expense = ExpenseController.getInstance().getSelectedExpense();
		try {
			expenseName.setText(expense.getName());
			expenseDescription.setText(expense.getDescription());;
			crncySpinner.setSelection(ctgryAdapter.getPosition(expense.getAmount().getCurrency().toString()));
			ctgrySpinner.setSelection(crncyAdapter.getPosition(expense.getCategory()));
			expenseAmount.setText(Double.toString(expense.getAmount().getNumber()));
			completeCheck.setChecked(expense.getComplete());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		date = ExpenseController.getInstance().getExpenseDate();
		expenseYear = date.get(GregorianCalendar.YEAR);
		expenseMonth = date.get(GregorianCalendar.MONTH);
		expenseDay = date.get(GregorianCalendar.DAY_OF_MONTH);
		
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
	
	private final DatePickerDialog.OnDateSetListener expenseDateListener
	= new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int year, int month, int day) {
			expenseYear = year;
			expenseMonth = month;
			expenseDay=day;
			date.set(year,month,day);
			showDate();
		}
	};

	private void showDate() {
		expenseDateView.setText(new StringBuilder().append(expenseMonth+1).append("-").append(expenseDay).append("-").append(expenseYear));
   }
	//*******************************************************************************
	
	public void onClick_AddExpense(View v){
		String name = expenseName.getText().toString();
		String description = expenseDescription.getText().toString(); 
		double amount;

		if (expenseAmount.getText().toString().isEmpty()){
			Toast.makeText(this, "Amount being set to 0", Toast.LENGTH_LONG).show();
			amount = Double.valueOf("0.0");}

		else
			amount = Double.valueOf(expenseAmount.getText().toString());
		String category = ctgrySpinner.getSelectedItem().toString();
		String currency = crncySpinner.getSelectedItem().toString();
		boolean complete = completeCheck.isChecked();
		if (name.isEmpty()){
			Toast.makeText(this, "Name is a Mandatory Field", Toast.LENGTH_LONG).show();
		}
		else if ((ExpenseController.getInstance().containsByName(name))){
			Toast.makeText(this, "An Expense With This Name Already Exists", Toast.LENGTH_LONG).show();
		}
		/* NOTE: expense date doesn't have to be inside Claim's date range (e.g. buying a plane ticket before
		 * the trip begins, or paying for something after the fact. The profs clarified on this in one of the forum posts. -John
		
		else if (date.compareTo(ClaimController.getInstance().getSelectedClaim().getStartDate()) < 0){
			Toast.makeText(this, "Invalid Date (Before Claim Start Date)", Toast.LENGTH_LONG).show();

		}
		else if (date.compareTo(ClaimController.getInstance().getSelectedClaim().getEndDate()) > 0){
			Toast.makeText(this, "Invalid Date (After Claim End Date)", Toast.LENGTH_LONG).show();
		}
		
		*/
		
		else{
			ExpenseController.getInstance().setExpense(category, date, amount,currency, description,name, complete);
			finish();}
	}
	public void modifyReceipt(View view) {
		startActivity(new Intent(ExpenseEditActivity.this, ReceiptAddActivity.class));
	}
}