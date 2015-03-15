package team14.expenseexpress.controller;



import android.content.Context;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;


public class ExpenseListController {
	
	private Context context;
	private ExpenseList expenseList;
	private static ExpenseListController instance;
	
	private ExpenseListController(){
	
	}
	
	public static ExpenseListController getInstance(){
		if (instance == null){
			instance = new ExpenseListController();
		}
		return instance;
	}
	
	private ExpenseListController(Claim claim){
		expenseList = claim.getExpenseList();
	
	}
	public void addExpense(Expense expense){
		expenseList.add(expense);
	}
	
	public void removeExpense(Expense expense){
		expenseList.remove(expense);
	}

	
}