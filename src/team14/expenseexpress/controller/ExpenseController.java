package team14.expenseexpress.controller;



import android.content.Context;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;


public class ExpenseController {
	
	private Context context;
	private ExpenseList expenseList;
	private static ExpenseController instance;
	
	private ExpenseController(){
	
	}
	
	public static ExpenseController getInstance(){
		if (instance == null){
			instance = new ExpenseController();
		}
		return instance;
	}
	
	private ExpenseController(Claim claim){
		expenseList = claim.getExpenseList();
	
	}
	public void addExpense(Expense expense){
		expenseList.add(expense);
	}
	
	public void removeExpense(Expense expense){
		expenseList.remove(expense);
	}

	
}