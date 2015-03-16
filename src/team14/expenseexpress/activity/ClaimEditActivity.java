package team14.expenseexpress.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import team14.expenseexpress.R;
import team14.expenseexpress.activity.TagListDialogFragment.TagsListAdapter;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimEditActivity extends Activity {

	private TextView startDateView;
	private TextView endDateView;
	private EditText claimNameText;
	private ListView tagListView;
	private ListView destinationListView;
	
	private ArrayAdapter<ClaimTag> tagAdapter;
	private ArrayAdapter<Destination> destinationAdapter;
	
	private int startYear, startMonth, startDay;
	private int endYear, endMonth, endDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_edit);

		startDateView = (TextView) findViewById(R.id.startDateText);
		endDateView = (TextView) findViewById(R.id.endDateText);
		claimNameText = (EditText) findViewById(R.id.newClaimNameText);
		
		tagListView = (ListView) findViewById(R.id.tagList);
		//final ArrayList<ClaimTag> tagList = new ArrayList<ClaimTag>(ClaimController.getInstance().getSelectedClaim().getTags());
		tagAdapter = new ArrayAdapter<ClaimTag>(this, R.layout.listtextview, ClaimController.getInstance().getSelectedClaim().getTags());
		tagListView.setAdapter(tagAdapter);
		
		destinationListView = (ListView) findViewById(R.id.destinationList);
		//final ArrayList<Destination> destinationList = new ArrayList<Destination>(ClaimController.getInstance().getSelectedClaim().getDestinations());
		destinationAdapter = new ArrayAdapter<Destination>(this, R.layout.listtextview, ClaimController.getInstance().getSelectedClaim().getDestinations());
		tagListView.setAdapter(destinationAdapter);
		
		Calendar calendar = ClaimController.getInstance().getSelectedClaim().getStartDate();
		startYear = calendar.get(Calendar.YEAR);
		startMonth = calendar.get(Calendar.MONTH);
		startDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar = ClaimController.getInstance().getSelectedClaim().getEndDate();
		endYear = calendar.get(Calendar.YEAR);
		endMonth = calendar.get(Calendar.MONTH);
		endDay = calendar.get(Calendar.DAY_OF_MONTH);
		showDate();
		
		try {
			claimNameText.setText(ClaimController.getInstance().getSelectedClaim().getName());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
	//*******************************************************************************
		//http://www.tutorialspoint.com/android/android_datepicker_control.htm   
		//All DatePicker code was implemented from this source, accessed Feb.1,2015
		@SuppressWarnings("deprecation")
		public void setStartDate(View view) {
			showDialog(777);
			Toast.makeText(getApplicationContext(), "Start Date", Toast.LENGTH_SHORT).show();
		}
		
		@SuppressWarnings("deprecation")
		public void setEndDate(View view) {
			showDialog(999);
			Toast.makeText(getApplicationContext(), "End Date", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		protected Dialog onCreateDialog(int id) {
			if (id == 777) {
				return new DatePickerDialog(this, startDateListener, startYear, startMonth, startDay);
			}
			if (id==999) {
				return new DatePickerDialog(this, endDateListener, endYear, endMonth, endDay);
			}
			return null;
		}

		private DatePickerDialog.OnDateSetListener startDateListener
		= new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker arg0, int year, int month, int day) {
				startYear = year;
				startMonth = month;
				startDay=day;
				Calendar date = Calendar.getInstance();
				date.set(year,month,day);
				ClaimController.getInstance().getSelectedClaim().setStartDate((GregorianCalendar)date);
				showDate();
			}
		};
		
		private DatePickerDialog.OnDateSetListener endDateListener
		= new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker arg0, int year, int month, int day) {
				endYear = year;
				endMonth = month;
				endDay=day;
				Calendar date = Calendar.getInstance();
				date.set(year,month,day);
				ClaimController.getInstance().getSelectedClaim().setEndDate((GregorianCalendar)date);
				showDate();
			}
		};

		private void showDate() {
			startDateView.setText(new StringBuilder().append(startMonth+1).append("-").append(startDay).append("-").append(startYear));
			endDateView.setText(new StringBuilder().append(endMonth+1).append("-").append(endDay).append("-").append(endYear));
	   }
		//*******************************************************************************
		
		@Override
		protected void onStart() {
			super.onStart();
		}
		
		@Override
		protected void onResume() {
			super.onResume();
		}
		
		public void editTags(View v) {
			startActivity(new Intent(ClaimEditActivity.this,EditTags.class));
		}
		
		public void editDestinations(View v) {
			startActivity(new Intent(ClaimEditActivity.this,EditDestinations.class));
		}

		public void editClaim(View v) {
			
			finish();
		}

		

}
