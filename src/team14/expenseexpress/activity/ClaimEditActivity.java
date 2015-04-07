
package team14.expenseexpress.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import team14.expenseexpress.util.LocalFileHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
	private AlertDialog confirmDialog;
	
	private ArrayAdapter<ClaimTag> tagAdapter;
	private ArrayAdapter<Destination> destinationAdapter;
	
	private int startYear, startMonth, startDay;
	private int endYear, endMonth, endDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_edit);
		ConfirmDialog();

		startDateView = (TextView) findViewById(R.id.startDateText);
		endDateView = (TextView) findViewById(R.id.endDateText);
		claimNameText = (EditText) findViewById(R.id.newClaimNameText);
		
		tagListView = (ListView) findViewById(R.id.tagList);
		tagAdapter = new ArrayAdapter<ClaimTag>(this, R.layout.listtextview, ClaimController.getInstance().getSelectedClaim().getTags());
		tagListView.setAdapter(tagAdapter);
		
		destinationListView = (ListView) findViewById(R.id.destinationList);
		destinationAdapter = new ArrayAdapter<Destination>(this, R.layout.listtextview, ClaimController.getInstance().getSelectedClaim().getDestinations());
		destinationListView.setAdapter(destinationAdapter);
		
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
		/**
		 * Sets starting date by assigning appropriate id
		 * @param view View
		 */
		public void setStartDate(View view) {
			showDialog(777);
			Toast.makeText(getApplicationContext(), "Start Date", Toast.LENGTH_SHORT).show();
		}
		
		@SuppressWarnings("deprecation")
		/**
		 * Sets ending date by assigning appropriate id
		 * @param view View
		 */
		public void setEndDate(View view) {
			showDialog(999);
			Toast.makeText(getApplicationContext(), "End Date", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		/**
		 * Uses given id from either setStartDate or setEndDate
		 */
		protected Dialog onCreateDialog(int id) {
			if (id == 777) {
				return new DatePickerDialog(this, startDateListener, startYear, startMonth, startDay);
			}
			if (id==999) {
				return new DatePickerDialog(this, endDateListener, endYear, endMonth, endDay);
			}
			return null;
		}

		private final DatePickerDialog.OnDateSetListener startDateListener
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
		
		private final DatePickerDialog.OnDateSetListener endDateListener
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
		/**
		 * Sets the shown date for DatePicker
		 */
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
			tagAdapter.notifyDataSetChanged();
			destinationAdapter.notifyDataSetChanged();
			ClaimController.getInstance().saveClaims();
		}
		/**
		 * Changes the user's view to the EditTags activity
		 * @param v View
		 */
		public void editTags(View v) {
			startActivity(new Intent(ClaimEditActivity.this,EditTags.class));
		}
		/**
		 * Changes the user's view to the EditTags activity
		 * @param v View
		 */
		public void editDestinations(View v) {
			startActivity(new Intent(ClaimEditActivity.this,EditDestinations.class));
		}

		@Override
		public void onBackPressed() {
			//finishEdit();
			confirmDialog.show();
		}
		/**
		 * Presents the user with 'Yes' and 'No' buttons when asked if
		 * they want to discard their changes
		 */
		private void ConfirmDialog() {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    	builder.setMessage("Discard any Changes?")
		    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (ClaimListActivity.edit == false) {
							Claim claim = ClaimController.getInstance().getSelectedClaim();
							ClaimController.getInstance().getClaimList().remove(claim);
							ClaimListActivity.edit = true;
							finish();
						} else {
							finish();
						}
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		    	confirmDialog = builder.create();
		}
		/**
		 * Allows user to edit a claim's information
		 * @param v View
		 */
		public void editClaim(View v) {
			finishEdit();
		}
		/**
		 * Displays text to user depending on completion of information inputs
		 */
		private void finishEdit() {
			Claim claim = ClaimController.getInstance().getSelectedClaim();
			try {
				claim.setName(claimNameText.getEditableText().toString());
			} catch (NullPointerException e) {
				e.printStackTrace();
				Toast.makeText(this, "Name required", Toast.LENGTH_SHORT).show();
				return;
			}
			if(claimNameText.getEditableText().toString().isEmpty()) {
				Toast.makeText(this, "Name required", Toast.LENGTH_SHORT).show();
				return;
			} else if(claim.getEndDate().compareTo(claim.getStartDate()) < 0) {
				Toast.makeText(this, "End date can't come before start date.", Toast.LENGTH_SHORT).show();
				return;
			} else {
				LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
				Toast.makeText(this, "Claim edited.", Toast.LENGTH_SHORT).show();
				finish();
			}
		}
}
