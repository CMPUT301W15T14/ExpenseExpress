package team14.expenseexpress.controller;

import java.util.ArrayList;

import team14.expenseexpress.App;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.model.Claim;

public class ClaimController {
	private App app;
	private ArrayList<Claim> claims;
	
	//Singleton
	private ExpenseExpressActivity activity;	
	
	private static ClaimController instance;
	
	private ClaimController(ExpenseExpressActivity activity){
		this.activity = activity;
		app = (App) activity.getApplication();
		claims = app.getClaims();
	}
	
	public ClaimController getInstance(ExpenseExpressActivity activity){
		if (instance == null){
			instance = new ClaimController(activity);
		}
		return instance;
	}
	
	
	public void sortClaimsByDate(){
		
	}
}