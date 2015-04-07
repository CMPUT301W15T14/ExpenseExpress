package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.ApproverComment;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Status;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class ReturnClaimActivity extends ExpenseExpressActivity {
	RadioButton approved;
	RadioButton returned;
	TextView comment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approve_return);
		approved = (RadioButton) findViewById(R.id.approvedRadio);
		returned = (RadioButton) findViewById(R.id.returnedRadio);
		comment =  (TextView) findViewById(R.id.approveReturnCommentField);
	}
	
	public void onClick_returnClaim(View v) {
		Claim claim = ClaimController.getInstance().getSelectedClaim();
		if (approved.isChecked()) {
			claim.setStatus(Status.APPROVED);
			if (comment.getText() == null) {
				toast("You must add a comment");
			} else {
				ApproverComment approvercomment = new ApproverComment();
				approvercomment.setApproverName(UserController.getInstance().getCurrentUser().getName());
				approvercomment.setComment(comment.getText().toString());
				ArrayList<ApproverComment> commentList = claim.getApproverComments();
				commentList.add(approvercomment);
				claim.setApproverComments(commentList);
			}
		} else if (returned.isChecked()) {
			claim.setStatus(Status.RETURNED);
			if (comment.getText() == null) {
				toast("You must add a comment");
			} else {
				ApproverComment approvercomment = new ApproverComment();
				approvercomment.setApproverName(UserController.getInstance().getCurrentUser().getName());
				approvercomment.setComment(comment.getText().toString());
				ArrayList<ApproverComment> commentList = claim.getApproverComments();
				commentList.add(approvercomment);
				claim.setApproverComments(commentList);
			}
		} else {
			toast("Please select a option before submitting");
		}
		ElasticSearchHelper.getInstance().addClaim(claim);
	}
}
