package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressActivity;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.ApproverComment;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Activity for the approver returning an expense claim.
 * @author  Team 14
 * @date  April 6, 2015
 * @version  1.5
 */ 
public class ReturnClaimActivity extends ExpenseExpressActivity {
	CheckBox approved;
	CheckBox returned;
	TextView comment;
	/**
	 */
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
	/**
	 * changes boolean if checkbox A is clicked
	 * @param v View
	 */
	//nice method name ya lazy fuck
	public void onCick_checkBoxA(View v) {
		approved = (CheckBox) v;
		if (approved.isChecked()) {
			claim.setStatus("Approved");
			checkedA = true;
		} else {
			checkedA = false;
		}
	}
	/**
	 * changes boolean if checkbox A is clicked
	 * @param v View
	 */
	public void onCick_checkBoxR(View v) {
		returned = (CheckBox) v;
		if (returned.isChecked()) {
			claim.setStatus("Returned");
			checkedR = true;
		} else {
			checkedR = false;
		}
	}
	/**
	 * tells user what is left to fill out before pressing button again
	 * Submits claim otherwise, before leaving activity for previous activity
	 * @param v View
	 */
	public void onClick_returnClaim(View v) {

		if (comment.getText().toString().equals("")) {
			toast("You must add a comment");
		} else if (!checkedA && !checkedR) {
			toast("You must pick an option");
		} else if (checkedA && checkedR) {
			toast("Only one option allowed");
		} else if (comment.getText().toString().isEmpty()){
			toast("You must leave a comment");
		}
			else {
			ApproverComment approvercomment = new ApproverComment();
			approvercomment.setApproverName(UserController.getInstance().getCurrentUser().getName());
			approvercomment.setComment(comment.getText().toString());
			ArrayList<ApproverComment> commentList = claim.getApproverComments();
			commentList.add(approvercomment);
			claim.setApproverComments(commentList);
			claim.setApprover(UserController.getInstance().getCurrentUser());
			claim.addApproverToList(UserController.getInstance().getCurrentUser().getName());
			ElasticSearchHelper.getInstance().deleteClaim(claim);
			ElasticSearchHelper.getInstance().addClaim(claim);
			startActivity(new Intent(ReturnClaimActivity.this,ClaimListActivity.class));
		}
	} 
}
