
package team14.expenseexpress.activity;
//no javadoc here yet!!!!
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.UserController;
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

/*
public class NewClaimActivity extends ExpenseExpressActivity {
	
	private static EditText StartDateEdit;
	private static EditText EndDateEdit;
	private static EditText nameView;
	
	private static GregorianCalendar startDate; 
	private static GregorianCalendar endDate;  
	
	int startYear, startMonth, startDay;
	int endYear, endMonth, endDay;
	
	private static boolean Start;
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
		nameView = (EditText) findViewById(R.id.newClaimNameText);
		final ListView Destlistview = (ListView) findViewById(R.id.DestinationListView);
		final ListView Taglistview = (ListView) findViewById(R.id.tagListView);
		
		ClaimController controller = ClaimController.getInstance();
		
		DestinationList = controller.getSelectedClaim().getDestinations();
		chosenTags = controller.getSelectedClaim().getTags();
		
		Dname = new ArrayList<String>();
		for(Destination d:DestinationList) {
			Dname.add(d.getDestination());
			}
		
		Tname = new ArrayList<String>();
		for(ClaimTag t:chosenTags) {
			Tname.add(t.getName());
		}
		
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
		
		//if(ClaimController.getInstance().getSelectedClaim() != null) {
		Claim claim = ClaimController.getInstance().getSelectedClaim();
		StartDateEdit.setText(claim.startDateToString());
		EndDateEdit.setText(claim.endDateToString());
		nameView.setText(claim.getName());
		Destadapter.notifyDataSetChanged();
		Tagadapter.notifyDataSetChanged();
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
				startDate = new GregorianCalendar(year, month, day);
			} else {
				EndDateEdit.setText(day + "/" + (month + 1) + "/" + year);
				endDate = new GregorianCalendar(year, month, day);
			}
			
		}
	}
	

	public void addClaim(View v) {
		Claim claim = ClaimController.getInstance().getSelectedClaim();
		
		claim.setName(nameView.getText().toString());
		claim.setStartDate(startDate);
		claim.setEndDate(endDate);
		for (int i = 0; i < DestinationList.size(); i++) {
			claim.addDestination(DestinationList.get(i));
		}
		for (int i = 0; i < chosenTags.size(); i++) {
			claim.addTag(chosenTags.get(i));
		}
		if (claim.getTags().size() == 0) {
			claim.addTag(new ClaimTag(""));
			claim.setStatus(Status.INCOMPLETE);
		} else if (claim.getDestinations().size() == 0) {
			claim.addDestination(new Destination(""));
			claim.setStatus(Status.INCOMPLETE);
		} else {
			claim.setStatus(Status.IN_PROGRESS);
		}
		
		if(claim.getName().isEmpty()) {
			Toast.makeText(this, "Claim must have a name", Toast.LENGTH_SHORT).show();
			return;
		} else if((claim.getEndDate() == null)||(claim.getStartDate() == null)) {
			Toast.makeText(this, "Must add dates.", Toast.LENGTH_SHORT).show();
			return;
		} else if(claim.getEndDate().compareTo(claim.getStartDate()) < 0) {
			Toast.makeText(this, "End date can't come before start date.", Toast.LENGTH_SHORT).show();
			return;
		} else {
			//ClaimController.getInstance().addClaim(claim);
			Toast.makeText(this, "Adding a Claim", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	

	public void addDestination(View v) {
		FragmentManager fm = getFragmentManager();
		new NewDestinationDialogFragment().show(fm, "tagsListDialogFragment");
	}
	

	public void addTags(View v) {
		FragmentManager fm = getFragmentManager();
		new NCTagListDialogFragment(this).show(fm, "tagsListDialogFragment");
	}
	

	public void setTagsListAdapter1(NCTagsListAdapter adapter) {
		// TODO Auto-generated method stub
		this.tagsListAdapter = adapter;
	}
	

	public ArrayList<ClaimTag> getChosenTags() {
		return chosenTags;
	}
	
	
	public void updateAdapter() {
		// TODO Auto-generated method stub
		Tadapter.notifyDataSetChanged();
	}

}
*/