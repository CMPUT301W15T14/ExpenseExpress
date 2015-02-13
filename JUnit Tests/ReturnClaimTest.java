import java.util.ArrayList;

import team14.teamproject.ApproverController;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import team14.teamproject.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;


public class ReturnClaimTest extends 
		ActivityInstrumentationTestCase2<MainActivity> {
	
	public ClaimModel claim;
	public ClaimantController controller;
	public ApproverController approver;
	
	public ReturnClaimTest(){
		super(MainActivity.class);
	}
	
	public returnedClaimTest() {
		
		claim = new ClaimModel();
		controller = new ClaimantController("Bob");
		approver = new ApproverController("Joe");
		controller.addClaim(claim);
		controller.setClaim(0);
		approver.setStatus("Returned")
		controller.setApproverComment("Returned Claim");
		assertEquals("Status not equal Returned",controller.getStatus(), "Returned");
		assertEquals("No approver name", controller.getApproverName(), "Joe");
		asserEquals("No approver comment", controller.getComment(), "Returned Claim");
	}
}
