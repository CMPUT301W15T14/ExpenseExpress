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
	//Claim claim;
	boolean isApproved;
	boolean isReturned;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approve_return);
		approved = (CheckBox) findViewById(R.id.approvedRadio);
		returned = (CheckBox) findViewById(R.id.returnedRadio);
		comment =  (TextView) findViewById(R.id.approveReturnCommentField);
	}
	/**
	 * changes boolean if isReturned is clicked
	 * @param v View
	 */
	public void onCick_checkBoxA(View v) {
		approved = (CheckBox) v;
		if (approved.isChecked()) {
			isApproved = true;
		} else {
			isApproved = false;
		}
	}
	/**
	 * changes boolean if isRetuned is clicked
	 * @param v View
	 */
	public void onCick_checkBoxR(View v) {
		returned = (CheckBox) v;
		if (returned.isChecked()) {
			isReturned = true;
		} else {
			isReturned = false;
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
		} else if (!isApproved && !isReturned) {
			toast("You must pick an option");
		} else if (isApproved && isReturned) {
			toast("Only one option allowed");
		} else if (comment.getText().toString().isEmpty()){
			toast("You must leave a comment");
		}
		else {
			Claim claim = ClaimController.getInstance().getSelectedClaim();
			
			ApproverComment approvercomment = new ApproverComment();
			approvercomment.setApproverName(UserController.getInstance().getCurrentUser().getName());
			approvercomment.setComment(comment.getText().toString());
			claim.getApproverComments().add(approvercomment);
			claim.setApprover(UserController.getInstance().getCurrentUser());
			if(isApproved) {
				claim.setStatus(Status.APPROVED);
			} else if(isReturned) {
				claim.setStatus(Status.RETURNED);
			}
			ElasticSearchHelper.getInstance().deleteClaim(claim);
			ElasticSearchHelper.getInstance().addClaim(claim);
			startActivity(new Intent(ReturnClaimActivity.this,ClaimListActivity.class));
			finish();
		}
	} 
}
