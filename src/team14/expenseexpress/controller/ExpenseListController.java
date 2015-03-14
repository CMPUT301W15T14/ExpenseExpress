package team14.expenseexpress.controller;



import android.content.Context;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;


public class ExpenseListController {
	
	private Context context;
	private ExpenseList expenseList;

	
	private ExpenseListController(Claim claim){
		expenseList = claim.getExpenseList();
	
	}
	public void addExpense(Expense expense){
		expenseList.addExpense(expense);
	}
	
	public void removeExpense(Expense expense){
		expenseList.remove(expense);
	}

	
}