import team14.teamproject.ApproverController;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import android.test.ActivityInstrumentationTestCase2;


public class ApproveClaimTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public ClaimModel claim;
	public ClaimantController controller;
	public ApproverController approver;
	
	public ApproveClaimTest(){
		super(MainActivity.class);
	}
	

	public approvedClaimTest() {
		
		claim = new ClaimModel();
		controller = new ClaimantController("Bob");
		approver = new ApproverController("Joe");
		controller.addClaim(claim);
		controller.setClaim(0);
		controller.setStatus("Approved");
		controller.setApproverComment("Approved Claim");
		assertEquals("Status not equal Returned",controller.getStatus(), "Approved");
		assertEquals("No approver name", controller.getApproverName(), "Joe");
		asserEquals("No approver comment", controller.getComment(), "Approved Claim");
	}
}
