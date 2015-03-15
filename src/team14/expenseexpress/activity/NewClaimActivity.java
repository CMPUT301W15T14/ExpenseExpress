package team14.expenseexpress.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class NewClaimActivity extends ExpenseExpressActivity {
	
	private static EditText StartDateEdit;
	private static EditText EndDateEdit;
	private static GregorianCalendar startDate;  
	private static boolean Start;
	private static ArrayList<Destination> DestinationList;
	private static ArrayList<String> Dname;
	private static ArrayList<ClaimTag> chosenTags;
	private ArrayAdapter<?> Dadapter;
	private ArrayAdapter<?> Tadapter;
	private boolean tags = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		
		StartDateEdit = (EditText) findViewById(R.id.tempStartDateTextfield);
		EndDateEdit = (EditText) findViewById(R.id.tempEndDateTextField);
		
		DestinationList = new ArrayList<Destination>();
		Dname = new ArrayList<String>();
		chosenTags = new ArrayList<ClaimTag>();
		
		final ListView Destlistview = (ListView) findViewById(R.id.DestinationListView);
		final ListView Taglistview = (ListView) findViewById(R.id.tagListView);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayAdapter Destadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, Dname);
		setDadapter(Destadapter);
		Destlistview.setAdapter(Destadapter);
		Destlistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
         });
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayAdapter Tagadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, chosenTags);
		tags = true;
		setTadapter(Tagadapter);
		Taglistview.setAdapter(Tagadapter);
		Taglistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
         });
		LayoutInflater.from(this);
		
	}
	
	private void setDadapter(ArrayAdapter adapter) {
		// TODO Auto-generated method stub
		this.Dadapter = adapter;
	}
	
	private void setTadapter(ArrayAdapter adapter) {
		// TODO Auto-generated method stub
		this.Tadapter = adapter;
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
			
			final View v = inflater.inflate(R.layout.activity_add_destinations, container, false);
			final EditText nameText = (EditText) v.findViewById(R.id.addDestinationTextField);
			final EditText reasonText = (EditText) v.findViewById(R.id.addReasonTextField);
			v.findViewById(R.id.addDestinationButton2).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							String name = nameText.getText().toString();
							String reason = reasonText.getText().toString();
							if (name.length() == 0 || reason.length() == 0) {
								toast("Empty Field");
							} else {
								Destination destination = new Destination(name);
								destination.setReason(reason);
								destination.setDestination(name);
								// check if it's already in list
								if (Dname.contains(name)) {
									toast("Already in List");
									return;
								}
								// if all good, add to list and dismiss dialog
								DestinationList.add(destination);
								Dname.add(name);
								toast("Added to List");
								Dadapter.notifyDataSetChanged();
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
		FragmentManager fm = getFragmentManager();
		new NewDestinationDialogFragment().show(fm, "tagsListDialogFragment");
	}
	
	public void addTags(View v) {
		FragmentManager fm = getFragmentManager();
		new NewDestinationDialogFragment().show(fm, "tagsListDialogFragment");
	}

}
