package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.R.layout;
import team14.expenseexpress.R.menu;
import team14.expenseexpress.controller.ClaimController;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ClaimDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_details);
		// Show the Up button in the action bar.
		
		TextView claimNameField = (TextView) findViewById(R.id.detailsName);
		claimNameField.setText(ClaimController.getInstance().getSelectedClaim().getName());
		
		TextView startDateField = (TextView) findViewById(R.id.detailsStartDate);
		startDateField.setText(ClaimController.getInstance().getSelectedClaim().startDateToString());
		
		TextView endDateField = (TextView) findViewById(R.id.detailsEndDate);
		endDateField.setText(ClaimController.getInstance().getSelectedClaim().endDateToString());
		
		TextView claimStatusField = (TextView) findViewById(R.id.claimDetailsStatus);
		claimStatusField.setText(ClaimController.getInstance().getSelectedClaim().getStatus());
		
		ListView destinationsList = (ListView) findViewById(R.id.claimListofDestinations);
		ArrayAdapter<String> destinationsAdapter = 
			    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ClaimController.getInstance().getSelectedClaim().getDestinationsNames());
		destinationsList.setAdapter(destinationsAdapter);
		
		
	}


}
