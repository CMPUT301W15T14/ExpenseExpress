package team14.expenseexpress.test;

import java.util.ArrayList;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.model.ApproverComment;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Status;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Test the Approver Model to check if the approver can return/approve a claim and check
 * if the approver can add a comment to the claim
 * Related Use Case: UC21, UC22, UC27
 *
 */
public class TestApproverModel extends ActivityInstrumentationTestCase2<ClaimListActivity> {

	public TestApproverModel() {
		super(ClaimListActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Test the ApproverComment Mode. If this works then adding a comment to a
	 * claim should also work
	 */
	public void testApproverCommentModel() {
		ApproverComment approvComment = new ApproverComment();
		approvComment.setComment("First Approver Comment");
		assertEquals("ApproverComment model get comment does not work", "First Approver Comment", approvComment.getComment());
		assertNotNull("ApproverComment model get timestamp does not work", approvComment.getTimestamp());
	}
	/**
	 * Test to check if the approver can return a claim or approve a claim and add
	 * a comment
	 * Related Use Case: UC27
	 */
	public void testReturnApproveCaim() {
		ArrayList<Claim> claims = ElasticSearchHelper.getInstance().getSubmitted();
		Claim claim = claims.get(0);
		claim.setStatus(Status.APPROVED);
		assertEquals("claim set status to approved does not work", "Approved", claim.getStatus());
		claim.setStatus(Status.RETURNED);
		assertEquals("claim set status to approved does not work", "Returned", claim.getStatus());
		
		
	}
	
}
