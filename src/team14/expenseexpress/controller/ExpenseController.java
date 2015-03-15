package team14.expenseexpress.controller;

import team14.expenseexpress.ExpenseExpressActivity;

public class ExpenseController {

	private ExpenseExpressActivity activity;
	
	private static ExpenseController instance;
	
	private ExpenseController(ExpenseExpressActivity activity){
		this.activity = activity;
	}
	
	public static ExpenseController getInstance(ExpenseExpressActivity activity){
		if (instance == null){
			instance = new ExpenseController(activity);
		}
		return instance;
	}
	
}
