package team14.expenseexpress.controller;

import java.util.ArrayList;

import team14.expenseexpress.App;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.model.Claim;

public class ClaimController {
	private App app;
	private ArrayList<Claim> localClaims;
	private ArrayList<Claim> remoteClaims;
	
	//Singleton
	private ExpenseExpressActivity activity;	
	
	private static ClaimController instance;
	
	private ClaimController(ExpenseExpressActivity activity){
		this.activity = activity;
		app = (App) activity.getApplication();
		localClaims = app.getLocalClaims();
		remoteClaims = app.getRemoteClaims();
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