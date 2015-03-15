package team14.expenseexpress.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class NewClaimActivity extends Activity {
	
	private static EditText StartDateEdit;
	private static EditText EndDateEdit;
	private static GregorianCalendar startDate;  
	private static boolean Start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		
		StartDateEdit = (EditText) findViewById(R.id.tempStartDateTextfield);
		EndDateEdit = (EditText) findViewById(R.id.tempEndDateTextField);
	}
	
	public void showDatePickerDialog(View v) {
		
		if (v == StartDateEdit) {
			Start = true;
		} else {
			Start = false;
		}
		
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}
 
	public static class DatePickerFragment extends DialogFragment
    				implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			if (Start) {
				StartDateEdit.setText(day + "/" + (month + 1) + "/" + year);
			} else {
				EndDateEdit.setText(day + "/" + (month + 1) + "/" + year);
			}
			startDate = new GregorianCalendar(year, month, day);
		}
	}
	
	public void addClaim(View v) {
		ClaimController cListController = ClaimController.getInstance();
		cListController.initialize(this);
		Toast.makeText(this, "Adding a Claim", Toast.LENGTH_SHORT).show();
		Claim claim = new Claim();
		EditText nameView = (EditText) findViewById(R.id.newClaimNameText);
		claim.setName(nameView.getText().toString());
		claim.setStartDate(startDate);
		cListController.addClaim(claim);
		finish();
	}
	
	public void addDestination(View v) {
		
	}

}
