package team14.expenseexpress.test;

import java.util.GregorianCalendar;

import team14.expenseexpress.activity.ClaimDetailsActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Destination;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class ViewClaimTest extends
		ActivityInstrumentationTestCase2<ClaimDetailsActivity> {
	
	Instrumentation instrumentation;
	ClaimDetailsActivity activity;

	
	public ViewClaimTest() {
		super(ClaimDetailsActivity.class);;
		instrumentation = getInstrumentation();
		activity = getActivity();
	}
	
	public void testViewClaimDetails() {
		
		final TextView claimName = (TextView) activity.findViewById(team14.expenseexpress.R.id.detailsName);
    	final TextView claimStart = (TextView) activity.findViewById(team14.expenseexpress.R.id.detailsStartDate);
    	final TextView claimEnd = (TextView) activity.findViewById(team14.expenseexpress.R.id.detailsEndDate);
    	final TextView claimStatus = (TextView) activity.findViewById(team14.expenseexpress.R.id.claimDetailsStatus);
    	
    	GregorianCalendar startdate = new GregorianCalendar(15, 3, 16);
		GregorianCalendar enddate = new GregorianCalendar(15, 3, 17);
		
		Claim claim = ClaimController.getInstance().getNewClaim();
		claim.setName("First Claim");


		Destination destination1 = new Destination();
		Destination destination2 = new Destination();
		destination1.setDestination("USA");
		destination2.setDestination("Canada");
		
		claim.addDestination(destination1);
		claim.addDestination(destination2);
		claim.setStartDate(startdate);
		claim.setEndDate(enddate);
		
		claimName.setText(claim.getName());
		claimStart.setText(claim.getStartDateString());
		claimEnd.setText(claim.getEndDateString());
		claimStatus.setText(claim.getStatus());
		
    	
	}

}
