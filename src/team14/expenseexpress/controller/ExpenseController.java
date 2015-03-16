package team14.expenseexpress.controller;

import java.util.GregorianCalendar;

import android.content.Context;
import team14.expenseexpress.model.Amount;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;
import team14.expenseexpress.model.Receipt;


public class ExpenseController {
	
	private Context context;
	private ExpenseList expenseList;
	private Expense selectedExpense; 
	
	//singleton
	private static ExpenseController instance;
	
	private ExpenseController(){
		if(selectedExpense == null) {
			selectedExpense = new Expense();
		}
	}
	
	public static ExpenseController getInstance(){
		if (instance == null){
			instance = new ExpenseController();

		}
		return instance;
	}
	
	public void setExpense(String category,
						    GregorianCalendar expenseDate,
						    int amount,
						    String description,
						    Receipt receipt,
						    String name){
		selectedExpense.setCategory(category);
		selectedExpense.setExpenseDate(expenseDate);
		selectedExpense.setDescription(description);
		selectedExpense.setReceipt(receipt);
		Amount actualAmount = new Amount(amount, null);
		selectedExpense.setAmount(actualAmount);
		selectedExpense.setName(name);
		
	}
	
	public void setSelectedExpense (Expense expense){
		selectedExpense = expense;
	
	}
	public Expense getSelectedExpense(){
	return selectedExpense;
	}
	private ExpenseController(Claim claim){
		this.expenseList = claim.getExpenseList();
	
	}
	public void addExpense(Expense expense){
		this.expenseList.add(expense);
	}
	
	public void removeExpense(Expense expense){
		this.expenseList.remove(expense);
	}
	
	public GregorianCalendar getExpenseDate() {
		return this.selectedExpense.getExpenseDate();
	}
	
	public void setExpenseDate(GregorianCalendar calendar) {
		this.selectedExpense.setExpenseDate(calendar);
	}
	
}