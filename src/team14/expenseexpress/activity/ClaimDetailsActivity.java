package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.ApproverComment;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Shows the misc. details of an expense claim.
 * 
 * @author Team 14
 * @date April 6, 2015
 * @Vversion 1.5
 */
public class ClaimDetailsActivity extends Activity {
	double CAD = 0, USD = 0, EUR = 0, GBP = 0, CHF = 0, JPY = 0, CNY = 0;
	double number;
	ArrayList<String> amountListString = new ArrayList<String>();
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
		
		TextView Approver = (TextView) findViewById(R.id.claimListApproversTitle);
		if (ClaimController.getInstance().getSelectedClaim().getApprover().getName() != null){
			Approver.setText(ClaimController.getInstance().getSelectedClaim().getApprover().getName());
		}
		else{
			Approver.setVisibility(View.INVISIBLE);
		}
		
		ListView destinationsList = (ListView) findViewById(R.id.claimListofDestinations);
		ArrayAdapter<String> destinationsAdapter = 
			    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ClaimController.getInstance().getSelectedClaim().getDestinationsNames());
		destinationsList.setAdapter(destinationsAdapter);
		
		ListView totalAmountList = (ListView) findViewById(R.id.claimListofCurrency);
		ArrayList<String> amountlist = ClaimController.getInstance().getSelectedClaim().getTotalAmounts();
		ArrayAdapter<String> totalCostAdapter = 
			    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, amountlist);
		totalAmountList.setAdapter(totalCostAdapter);
	
		
		ListView approverList = (ListView) findViewById(R.id.claimListofApprovers);
		ArrayList<ApproverComment> approvers = ClaimController.getInstance().getSelectedClaim().getApproverComments();
		ArrayAdapter<ApproverComment> approverAdapter = 
			    new ArrayAdapter<ApproverComment>(this, android.R.layout.simple_list_item_1, approvers);
		approverList.setAdapter(approverAdapter);
	}
}
