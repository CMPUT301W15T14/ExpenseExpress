package team14.expenseexpress.controller;

import java.util.ArrayList;

import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.model.Claim;

public class ClaimController {
	
	
	//Singleton
	private ExpenseExpressActivity activity;	
	
	private static ClaimController instance;
	
	private ClaimController(ExpenseExpressActivity activity){
		this.activity = activity;
		mergeClaims();
	}
	
	private void mergeClaims() {
		// TODO
	}

	public ClaimController getInstance(ExpenseExpressActivity activity){
		if (instance == null){
			instance = new ClaimController(activity);
		}
		return instance;
	}
	
	
	
	private void sortClaimsByDate(){
		// TODO, should be called locally before displaying information
	}
}