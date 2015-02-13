import java.util.ArrayList;

import team14.teamproject.ApproverController;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import team14.teamproject.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;


public class SubmitClaimTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	public ClaimModel claim;
	public ClaimantController controller;
	public ApproverController approver;
	public ExpenseModel expense1;
	ArrayList<ExpenseModel> expenses;

	public SubmitClaimTest(){
		super(MainActivity.class);
	}
	
	public void submittedClaimTest() {
		expense1 = new ExpenseModel();
		claim = new ClaimModel();
		controller = new ClaimantController("Bob");
		
		//test to check if the program recognize an incomplete claim
		controller.addClaim(claim);
		controller.setClaim(0);
		controller.setExpense(0);
		controller.setStatus("submitted");
		assertFalse("incomplete", controller.completed());
		
		//test to check if a completed claim's status can change to submit
		controller.addDestination("Canada");
		controller.setReason("Canada", "I love Snow");
		controller.setStartDate(1, 1, 2000);
		controller.setEndDate(1, 2, 2000);
		expenses = claim.getExpenses();
		expenses.clear();
		expenses.add(expense1);
		controller.setStatus("submitted");
		assertEquals("Status not equal Approved",controller.getStatus(), "submitted");
	}
	
}
