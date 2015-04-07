package team14.expenseexpress.test;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.model.ApproverComment;
import android.test.ActivityInstrumentationTestCase2;

public class TestApproverModel extends ActivityInstrumentationTestCase2<ClaimListActivity> {

	public TestApproverModel() {
		super(ClaimListActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testApproverCommentModel() {
		ApproverComment approvComment = new ApproverComment();
		approvComment.setComment("First Approver Comment");
		assertEquals("ApproverComment model get comment does not work", "First Approver Comment", approvComment.getComment());
		assertNotNull("ApproverComment model get timestamp does not work", approvComment.getTimestamp());
	}
	
}
