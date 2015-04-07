package team14.expenseexpress.test;

import java.util.GregorianCalendar;

import team14.expenseexpress.activity.ClaimDetailsActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Status;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ListView;
import android.widget.TextView;
/**
 * Test to check if we can see a claim's details
 * Related Use Case: UC2, UC24
 *
 */
public class ViewClaimTest extends
		ActivityInstrumentationTestCase2<ClaimDetailsActivity> {
	
	Instrumentation instrumentation;

	
	public ViewClaimTest() {
		super(ClaimDetailsActivity.class);;
		this.instrumentation = getInstrumentation();
	}
	/*
	 * Test if claimant and approver can see a claim's details
	 */
	@UiThreadTest
	public void testViewClaimDetails() {
	
		final TextView claimName = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.detailsName);
    	final TextView claimStart = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.detailsStartDate);
    	final TextView claimEnd = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.detailsEndDate);
    	final TextView claimStatus = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.claimDetailsStatus);
    	final ListView claimDestination = (ListView) getActivity().findViewById(team14.expenseexpress.R.id.claimListofDestinations);
    	final ListView claimCosts = (ListView) getActivity().findViewById(team14.expenseexpress.R.id.claimListofCurrency);
    	final ListView claimApprovers = (ListView) getActivity().findViewById(team14.expenseexpress.R.id.claimListofApprovers);
    	
    	GregorianCalendar startdate = new GregorianCalendar(15, 3, 16);
		GregorianCalendar enddate = new GregorianCalendar(15, 3, 17);
		
		Claim claim = ClaimController.getInstance().getNewClaim();
		claim.setName("First Claim");


		claim.setStartDate(startdate);
		claim.setEndDate(enddate);
		claim.setStatus(Status.COMPLETE);
		
		claimName.setText(claim.getName());
		claimStart.setText(claim.getStartDateString());
		claimEnd.setText(claim.getEndDateString());
		claimStatus.setText(claim.getStatus());
		
		assertEquals("Cannot see Name", "First Claim", claimName.getText().toString());
		assertEquals("Cannot see Startdate", "4/16/15", claimStart.getText()); 
		assertEquals("Cannot see Enddate", "4/17/15", claimEnd.getText().toString()); 
		assertEquals("Cannot see Status", "Complete", claimStatus.getText().toString()); 
		assertNotNull("Cannot see Destination list", claimDestination);
		assertNotNull("Cannot see Costs list", claimCosts);
		assertNotNull("Cannot see Approver list", claimApprovers);
		 	
	}

}
