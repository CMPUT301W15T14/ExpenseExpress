package team14.expenseexpress.test;

import team14.expenseexpress.activity.ClaimDetailsActivity;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Status;
import android.test.ActivityInstrumentationTestCase2;
/**\
 * Test the status of a claim by checking if we can set the status to approved or returned
 * Related Use Cases: UC21, UC22
 *
 */
public class TestStatusModel extends
		ActivityInstrumentationTestCase2<ClaimDetailsActivity> {

	public TestStatusModel() {
		super(ClaimDetailsActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testStatus() {
		Claim claim = new Claim();
		
		/*
		 * Test Approved status
		 * Related Use Case:UC22 
		 */
		claim.setStatus(Status.APPROVED);
		assertEquals("claim set status to approved does not work", "Approved", claim.getStatus());
		
		/*
		 * Test Approved status
		 * Related Use Case:UC21
		 */
		claim.setStatus(Status.RETURNED);
		assertEquals("claim set status to approved does not work", "Returned", claim.getStatus());
		/*
		 * Test the rest of the status to see if it works
		 */
		claim.setStatus(Status.IN_PROGRESS);
		assertEquals("claim set status to 'In progress' does not work", "In progress", claim.getStatus());
		
		claim.setStatus(Status.COMPLETE);
		assertEquals("claim set status to complete does not work", "Complete", claim.getStatus());
		
		claim.setStatus(Status.INCOMPLETE);
		assertEquals("claim set status to Incomplete does not work", "Incomplete", claim.getStatus());
	}

}
