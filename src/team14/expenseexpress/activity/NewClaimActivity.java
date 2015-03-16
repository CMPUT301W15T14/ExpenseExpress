package team14.expenseexpress.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.activity.NCTagListDialogFragment.NCTagsListAdapter;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import team14.expenseexpress.model.Status;
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
/**
 * <p> View
 * <p> NewClaimActivity Class:
 *  This view is for adding a new claim. User can name the claim, add destinations,
 *  add a start date and end date, and add tags to the claim.
 */
public class NewClaimActivity extends ExpenseExpressActivity {
	
	private static EditText StartDateEdit;
	private static EditText EndDateEdit;
	private static GregorianCalendar startDate; 
	private static GregorianCalendar endDate;  
	private static boolean Start;
	private static Claim claim;
	private static ArrayList<Destination> DestinationList;
	private static ArrayList<String> Dname;
	private static ArrayList<ClaimTag> chosenTags;
	public ArrayList<String> Tname;
	private ArrayAdapter<?> Dadapter;
	private ArrayAdapter<?> Tadapter;
	private NCTagsListAdapter tagsListAdapter;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		
		StartDateEdit = (EditText) findViewById(R.id.tempStartDateTextfield);
		EndDateEdit = (EditText) findViewById(R.id.tempEndDateTextField);
		
		claim = new Claim();
		DestinationList = new ArrayList<Destination>();
		Dname = new ArrayList<String>();
		chosenTags = new ArrayList<ClaimTag>();
		Tname = new ArrayList<String>();
		
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
		ArrayAdapter Tagadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, Tname);
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
	
	/**
	 * A method to set the adapter for destination in order to notifyDataSetChanged() 
	 * @param adapter The ArrayAdapter used
	 */
	private void setDadapter(ArrayAdapter adapter) {
		// TODO Auto-generated method stub
		this.Dadapter = adapter;
	}
	
	/**
	 * A method to set the adapter for tags in order to notifyDataSetChanged() 
	 * @param adapter The ArrayAdapter used
	 */
	private void setTadapter(ArrayAdapter adapter) {
		// TODO Auto-generated method stub
		this.Tadapter = adapter;
	}
	
	/**
	 * A method that will create a date picker dialog for start date and end date
	 * @param v View
	 */
	public void showDatePickerDialog(View v) {
		
		if (v == StartDateEdit) {
			Start = true;
		} else {
			Start = false;
		}
		
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}
	/**
	 * A DialogFragment class that create a dialog for adding a new destination and a reason for this destination
	 * <p> Constraints: no empty fields
	 */
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
	
	/**
	 * A DialogFragment class for creating a date picker dialog to get the start date and end date
	 * <p> Source taken from: http://www.truiton.com/2013/03/android-pick-date-time-from-edittext-onclick-event/
	 */
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
				startDate = new GregorianCalendar(year, month, day);
			} else {
				EndDateEdit.setText(day + "/" + (month + 1) + "/" + year);
				endDate = new GregorianCalendar(year, month, day);
			}
			
		}
	}
	
	/**
	 * When the user press the "Add Claim" button, this method calls the ClaimController and adds the claim and its fields 
	 * to the claim list.
	 * @param v View
	 */
	public void addClaim(View v) {
		Claim claim = ClaimController.getInstance().getNewClaim();
		
		EditText nameView = (EditText) findViewById(R.id.newClaimNameText);
		claim.setName(nameView.getText().toString());
		claim.setStartDate(startDate);
		claim.setEndDate(endDate);
		for (int i = 0; i < DestinationList.size(); i++) {
			claim.addDestination(DestinationList.get(i));
		}
		for (int i = 0; i < chosenTags.size(); i++) {
			claim.addTag(chosenTags.get(i));
		}
		claim.setStatus(Status.IN_PROGRESS);
		ClaimController.getInstance().addClaim(claim);
		Toast.makeText(this, "Adding a Claim", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	/**
	 * When the user press the "Add destination" button, this method calls NewDestinationDialogFragment() in order to
	 * create a dialog for adding a new destination
	 * @param v View
	 */
	public void addDestination(View v) {
		FragmentManager fm = getFragmentManager();
		new NewDestinationDialogFragment().show(fm, "tagsListDialogFragment");
	}
	
	/**
	 * When the user press the "Add Tag" button, this method calls NCTagListDialogFragment() in order to
	 * create a dialog for selecting a tag to add to the claim
	 * @param v View
	 */
	public void addTags(View v) {
		FragmentManager fm = getFragmentManager();
		new NCTagListDialogFragment(this).show(fm, "tagsListDialogFragment");
	}
	
	/**
	 * A method to set the adapter for Tags in order to notifyDataSetChanged() 
	 * @param adapter The NCTagsListAdapter used
	 */
	public void setTagsListAdapter1(NCTagsListAdapter adapter) {
		// TODO Auto-generated method stub
		this.tagsListAdapter = adapter;
	}
	
	/**
	 * A method to get a ArrayList of tags chosen by the user called chosenTags
	 * @return chosenTags
	 */
	public ArrayList<ClaimTag> getChosenTags() {
		return chosenTags;
	}
	
	/**
	 * A method to update the tag list when a new tag is added by calling notifyDataSetChanged()
	 */
	public void updateAdapter() {
		// TODO Auto-generated method stub
		Tadapter.notifyDataSetChanged();
	}

}
