package team14.expenseexpress.activity;

import java.util.GregorianCalendar;

import team14.expenseexpress.HomeGeo;
import team14.expenseexpress.LocationActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.maps.MapActivity;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.receipt.ReceiptAddActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
	private CheckBox incompleteCheck;
	private Button receiptButton;
	private TextView expLat;
	private TextView expLong;
	
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
		incompleteCheck = (CheckBox) findViewById(R.id.completeCheck);
		receiptButton = (Button) findViewById(R.id.addReceipt);
		expLat = (TextView) findViewById(R.id.expenseLat);
		expLong = (TextView) findViewById(R.id.expenseLong);
		
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
			incompleteCheck.setChecked(expense.getIncomplete());
			expLat.setText("Latitude: " + String.valueOf(expense.getLatitude()));
			expLong.setText("Longitude : " + String.valueOf(expense.getLongitude()));
		} catch (Exception e) {
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
	/**
	 * Sets starting date by assigning appropriate id
	 * @param view View
	 */
	public void setExpenseDate(View view) {
		showDialog(777);
		Toast.makeText(getApplicationContext(), "Expense Date", Toast.LENGTH_SHORT).show();
	}

	@Override
	/**
	 * Uses given id from setExpenseDate
	 */
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
/**
 * Puts the stored date into the appropriate outputting format
 */
	private void showDate() {
		expenseDateView.setText(new StringBuilder().append(expenseMonth+1).append("-").append(expenseDay).append("-").append(expenseYear));
   }
	//*******************************************************************************
	/**
	 * Saves expense information if submitted information is correct
	 * @param v View
	 */
	public void onClick_AddExpense(View v){
		String name = expenseName.getText().toString();
		Toast.makeText(this, name, Toast.LENGTH_LONG).show();
		String description = expenseDescription.getText().toString(); 
		double amount;

		if (expenseAmount.getText().toString().isEmpty()){
			Toast.makeText(this, "Amount being set to 0", Toast.LENGTH_LONG).show();
			amount = Double.valueOf("0.0");}

		else
			amount = Double.valueOf(expenseAmount.getText().toString());
		String category = ctgrySpinner.getSelectedItem().toString();
		String currency = crncySpinner.getSelectedItem().toString();
		boolean incomplete = incompleteCheck.isChecked();
		ExpenseController.getInstance().setExpense(category, date, amount,currency, description,name, incomplete);
		if (name.isEmpty()){
			Toast.makeText(this, "Name is a Mandatory Field", Toast.LENGTH_LONG).show();
		}
		else if ((ExpenseController.getInstance().containsByName(name))){
			Toast.makeText(this, "An Expense With This Name Already Exists", Toast.LENGTH_LONG).show();
		}
		else{
			finish();}
	}

	
	public void onClick_GPS(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Which Geolocation Device?")
        .setNegativeButton("GPS", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(ExpenseEditActivity.this,
						LocationActivity.class);
				intent.putExtra("ID", "EXPENSE");
				startActivity(intent);
				
			}
		})
		.setPositiveButton("Map", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(ExpenseEditActivity.this,
						MapActivity.class);
				intent.putExtra("ID", "EXPENSE");
				startActivity(intent);
				
			}
		});
        builder.show(); 

	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		expLat.setText("Latitude: " + String.valueOf(ExpenseController.getInstance().getSelectedExpense().getLatitude()));
		expLong.setText("Longitude : " + String.valueOf(ExpenseController.getInstance().getSelectedExpense().getLongitude()));
		
	}
	
	
	

	/**
	 * Changes current activity to ReceiptAddActivity
	 * @param view View
	 */

	public void modifyReceipt(View view) {
		startActivity(new Intent(ExpenseEditActivity.this, ReceiptAddActivity.class));
	}
}