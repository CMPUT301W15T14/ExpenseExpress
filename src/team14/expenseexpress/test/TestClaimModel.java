package team14.expenseexpress.test;


import java.util.GregorianCalendar;

import team14.expenseexpress.activity.ClaimEditActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Destination;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Tests to check Claim and ClaimList model and its methods. Also test the ClaimController nad 
 * any other model related to claim model. 
 * <p>Related Use Cases: UC1, UC4
 */
public class TestClaimModel extends
		ActivityInstrumentationTestCase2<ClaimEditActivity> {
	
	private final ClaimController claimcontroller = ClaimController.getInstance();
	private Claim claim = claimcontroller.getNewClaim();
	
	
	public TestClaimModel() {
		super(ClaimEditActivity.class);
	}
	
	/**
	 * Test the destination model if this passes then adding a new destination to 
	 * a claim should also pass
	 */
	public void testDestinaionModel() {
		Destination destination = new Destination("Canada");
		destination.setReason("Cuz its cold");
		assertEquals("Destination model works", "Canada", destination.getDestination());
		assertEquals("Destination reason works", "Cuz its cold", destination.getReason());
	}
	/**
	 * Test the Claim and ClaimList model and its controller to see if we can add a new 
	 * claim with startdate, enddate, and multiple destinations and test if we can delete
	 * the claim
	 */
	public void testNewClaim() {
		GregorianCalendar startdate = new GregorianCalendar(15, 3, 16);
		GregorianCalendar enddate = new GregorianCalendar(15, 3, 17);

		claimcontroller.initialize(getActivity());
		
		claim.setName("First Claim");
		Destination destination1 = new Destination("Canada");
		Destination destination2 = new Destination("USA");
		claim.addDestination(destination1);
		claim.addDestination(destination2);
		claim.setStartDate(startdate);
		claim.setEndDate(enddate);
		claimcontroller.setSelectedClaim(claim);
		claim = claimcontroller.getSelectedClaim();
		assertEquals("getSelectedClaim() from claimcontroller does not work", "First Claim", claim.getName());
		claimcontroller.addClaim(claim);
		
		//Test if added properly
		assertFalse("claim controller and claimlist works adding a claim does not work", claimcontroller.getClaimList().size() == 0);
		assertEquals("claim startdate does not work", startdate, claim.getStartDate());
		assertEquals("claim enddate does not work", enddate, claim.getEndDate());
		assertFalse("claim destination list dos not work", claim.getDestinations().size() == 0);
		assertEquals("claim get destination name does not work","Canada", claim.getDestinations().get(0).getDestination());
		assertEquals("claim get destination name does not work","USA", claim.getDestinations().get(1).getDestination());
		
		//Test Delete claim
		claimcontroller.removeClaim(claim);
		assertTrue("claim controller and claimlist deleting a claim does not work", claimcontroller.getClaimList().size() == 0);
	}
}
