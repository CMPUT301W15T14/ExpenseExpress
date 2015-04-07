package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.ApproverComment;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ReturnClaimActivity extends ExpenseExpressActivity {
	CheckBox approved;
	CheckBox returned;
	TextView comment;
	Claim claim;
	boolean checkedA;
	boolean checkedR;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approve_return);
		approved = (CheckBox) findViewById(R.id.approvedRadio);
		returned = (CheckBox) findViewById(R.id.returnedRadio);
		comment =  (TextView) findViewById(R.id.approveReturnCommentField);
		claim = ClaimController.getInstance().getSelectedClaim();
		toast(claim.getName());
	}
	public void onCick_checkBoxA(View v) {
		approved = (CheckBox) v;
		if (approved.isChecked()) {
			claim.setStatus("Approved");
			//toast("hello");
			checkedA = true;
		} else {
			claim.setStatus("Submitted");
			checkedA = false;
		}
	}
	public void onCick_checkBoxR(View v) {
		returned = (CheckBox) v;
		if (returned.isChecked()) {
			claim.setStatus("Returned");
			checkedR = true;
		} else {
			claim.setStatus("Submitted");
			checkedR = false;
		}
	}
	public void onClick_returnClaim(View v) {

		if (comment.getText().toString().equals("")) {
			toast("You must add a comment");
		} else if (!checkedA && !checkedR) {
			toast("You must pick an option");
		} else if (checkedA && checkedR) {
			toast("Only one option allowed");
		}	else {
			ApproverComment approvercomment = new ApproverComment();
			approvercomment.setApproverName(UserController.getInstance().getCurrentUser().getName());
			approvercomment.setComment(comment.getText().toString());
			ArrayList<ApproverComment> commentList = claim.getApproverComments();
			commentList.add(approvercomment);
			claim.setApproverComments(commentList);
			claim.setApprover(UserController.getInstance().getCurrentUser());
			claim.addApproverToList(UserController.getInstance().getCurrentUser().getName());
			ElasticSearchHelper.getInstance().addClaim(claim);
			finish();
		}
	} 
}
