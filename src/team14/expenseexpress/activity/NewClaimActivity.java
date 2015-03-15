package team14.expenseexpress.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Destination;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class NewClaimActivity extends ExpenseExpressActivity {
	
	private static EditText StartDateEdit;
	private static EditText EndDateEdit;
	private static GregorianCalendar startDate;  
	private static boolean Start;
	private static ArrayList<Destination> DestinationList;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		
		StartDateEdit = (EditText) findViewById(R.id.tempStartDateTextfield);
		EndDateEdit = (EditText) findViewById(R.id.tempEndDateTextField);
		
		LayoutInflater.from(this);
		
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
	@SuppressLint("ValidFragment")
	private class NewDestinationDialogFragment extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Dialog dialog = super.onCreateDialog(savedInstanceState);

			// request a window without the title
			dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			return dialog;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.activity_add_destinations, container, false);
			v.findViewById(R.id.addInDestinationButton).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							String name = ((EditText) v
									.findViewById(R.id.addDestinationTextField))
									.getText().toString();
							String reason = ((EditText) v
									.findViewById(R.id.addReasonTextField))
									.getText().toString();
							if (name.length() == 0 || reason.length() == 0) {
								toast("Empty Field");
							} else {
								Destination destination = new Destination(name);
								destination.setReason(reason);
								// check if it's already in list
								if (DestinationList.contains(destination)) {
									toast("Already in List");
									return;
								}
								// if all good, add to list and dismiss dialog
								DestinationList.add(destination);
								toast("Added to List");
								dismiss();
							}
						}
					});
			return v;
		}
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
		Toast.makeText(this, "Adding a Claim", Toast.LENGTH_LONG).show();
		FragmentManager fm = getFragmentManager();
		new NewDestinationDialogFragment().show(fm, "tagsListDialogFragment");
	}

}
