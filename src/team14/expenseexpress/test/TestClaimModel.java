package team14.expenseexpress.test;


import java.util.ArrayList;
import java.util.GregorianCalendar;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.TagListController;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import team14.expenseexpress.model.TagList;
import team14.expenseexpress.model.User;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Tests to check Claim and ClaimList model and its methods. Also test the ClaimController and 
 * any other model related to claim model. 
 * <p>Related Use Cases: UC1, UC3, UC4, UC7, UC9
 */
public class TestClaimModel extends
		ActivityInstrumentationTestCase2<ClaimListActivity> {

	
	public TestClaimModel() {
		super(ClaimListActivity.class);
	}
	
	/**
	 * Test the destination model if this passes then adding a new destination to 
	 * a claim should also pass
	 */
	public void testDestinaionModel() {
		Destination destination = new Destination();
		destination.setDestination("Canada");
		destination.setReason("Cuz its cold");
		assertEquals("Destination model works", "Canada", destination.getDestination());
		assertEquals("Destination reason works", "Cuz its cold", destination.getReason());
	}
	/**
	 * Test the Claim and ClaimList model and its controller to see if we can add a new 
	 * claim with startdate, enddate, and multiple destinations and test if we can delete
	 * the claim. Also test if we can add a tag to the claim and edit a claim.
	 */
	public void testNewClaim() {
		
		GregorianCalendar startdate = new GregorianCalendar(15, 3, 16);
		GregorianCalendar enddate = new GregorianCalendar(15, 3, 17);
		
		Destination destination1 = new Destination();
		Destination destination2 = new Destination();
		destination1.setDestination("Canada");
		destination2.setDestination("USA");

		User user = new User("Cheung");
		Mode.set(Mode.CLAIMANT);
		UserController.getInstance().setCurrentUser(user);
		ClaimController.getInstance().initialize(getActivity());
		Claim claim = ClaimController.getInstance().getNewClaim();
		/*
		 * Test add Claim
		 * Related Use Cases: UC1, UC7
		 */
		claim.setName("First Claim");
		claim.addDestination(destination1);
		claim.addDestination(destination2);
		claim.setStartDate(startdate);
		claim.setEndDate(enddate);
		claim.addTag(new ClaimTag("tag1"));
		
		ClaimController.getInstance().setSelectedClaim(claim);
		ClaimController.getInstance().addClaim(claim);
		
		assertEquals("claim get name does not work", "First Claim", claim.getName());
		assertFalse("claim controller and claimlist works adding a claim does not work", ClaimController.getInstance().getClaimList().size() == 0);
		assertEquals("claim get startdate does not work", startdate, claim.getStartDate());
		assertEquals("claim get enddate does not work", enddate, claim.getEndDate());
		assertFalse("claim get destination list dos not work", claim.getDestinations().size() == 0);
		assertEquals("claim get destination name does not work","Canada", claim.getDestinations().get(0).getDestination());
		assertEquals("claim get destination name does not work","USA", claim.getDestinations().get(1).getDestination());
		
		ArrayList<ClaimTag> tags = claim.getTags();
		assertEquals("add a tag to claim does not work","tag1", tags.get(0).getName());
		
		/*
		 * Test edit Claim
		 * Related Use Cases: UC3
		 */
		startdate = new GregorianCalendar(15, 4, 16);
		enddate = new GregorianCalendar(15, 4, 17);
		
		
		claim = ClaimController.getInstance().getSelectedClaim();
		assertEquals("claimcontroller getSelectedClaim does not work", "First Claim", claim.getName());
		
		claim.setName("edited Claim");
		claim.getDestinations().get(0).setDestination("China");
		claim.getDestinations().get(1).setDestination("Japan");
		claim.setStartDate(startdate);
		claim.setEndDate(enddate);
		
		assertEquals("claim get name does not work", "edited Claim", claim.getName());
		assertEquals("claim get startdate does not work", startdate, claim.getStartDate());
		assertEquals("claim get enddate does not work", enddate, claim.getEndDate());
		assertFalse("claim get destination list dos not work", claim.getDestinations().size() == 0);
		assertEquals("claim get destination name does not work","China", claim.getDestinations().get(0).getDestination());
		assertEquals("claim get destination name does not work","Japan", claim.getDestinations().get(1).getDestination());
		
		/*
		 * Test delete Claim
		 * Related Use Cases: UC4
		 */
		ClaimController.getInstance().removeClaim(claim);
		assertTrue("claim controller and claimlist deleting a claim does not work", ClaimController.getInstance().getClaimList().size() == 0);
	}
	
	public void testFilterClaimByTag() {
		

		/*
		 * Test filter claim by tags
		 * Related Use Cases: UC9
		 */
		ArrayList<Claim> filteredClaims1 = new ArrayList<Claim>();
		ArrayList<Claim> filteredClaims2 = new ArrayList<Claim>();
		
		Claim firstclaim = ClaimController.getInstance().getNewClaim();
		Claim secondclaim = ClaimController.getInstance().getNewClaim();
		
		TagList taglist = new TagList();
		ClaimTag tag1 = new ClaimTag("tag1");
		ClaimTag tag2 = new ClaimTag("tag2");
		taglist.add(tag1);
		taglist.add(tag2);

		firstclaim.setName("First Claim");
		firstclaim.addTag(tag1);
		
		secondclaim.setName("second Claim");
		secondclaim.addTag(tag2);
		
		User user = new User("Brandon");
		Mode.set(Mode.CLAIMANT);
		UserController.getInstance().setCurrentUser(user);
		ClaimController.getInstance().initialize(getActivity());
		ClaimController.getInstance().addClaim(firstclaim);
		ClaimController.getInstance().addClaim(secondclaim);
		ArrayList<Claim> claims = ClaimController.getInstance().getClaimList().getClaims();
		
		TagListController.getInstance().setChosenTags(taglist);
		ArrayList<ClaimTag> claimtags = TagListController.getInstance().getChosenTags().getTags();
		
		for (int i = 0; i < claims.size(); i++) {
			String claimtag = claims.get(i).getTags().get(0).getName();
			if (claimtag.equals(claimtags.get(0).getName())) {
				filteredClaims1.add(claims.get(i));
			} else if (claimtag.equals(claimtags.get(1).getName())) {
				filteredClaims2.add(claims.get(i));
			}
		}
		for (int i = 0; i < filteredClaims1.size(); i++) {
			String claimtag = filteredClaims1.get(i).getTags().get(0).getName();
			assertEquals("Claim not filtered with the tag 'tag1'","tag1", claimtag);
		}
		for (int i = 0; i < filteredClaims2.size(); i++) {
			String claimtag = filteredClaims2.get(i).getTags().get(0).getName();
			assertEquals("Claim not filtered with the tag 'tag2'","tag2", claimtag);
		}
	}
}
