package team14.expenseexpress.controller;

import java.util.GregorianCalendar;

import android.content.Context;
import team14.expenseexpress.model.Amount;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;
import team14.expenseexpress.model.Receipt;
import team14.expenseexpress.util.LocalFileHelper;


public class ExpenseController {
	
	private static ExpenseList expenseList;
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
	
	public static void initialize() {
		if (instance == null){
			instance = new ExpenseController();
		}
		expenseList = ClaimController.getInstance().getSelectedClaim().getExpenseList(); 
	}
	
	
	public void setExpense(String category,
						    GregorianCalendar expenseDate,
						    double amount,
						    String currency,
						    String description,
						    Receipt receipt,
						    String name){
		Expense expense = new Expense();
		expense.setCategory(category);
	    expense.setExpenseDate(expenseDate);
		expense.setDescription(description);
		expense.setReceipt(receipt);
		Amount actualAmount = new Amount(amount, null);
		expenseList.add(selectedExpense);
		expense.setAmount(actualAmount);
		expense.setName(name);
		this.expenseList.add(expense);
		LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
	}
	
	public void setSelectedExpense (Expense expense){
		this.selectedExpense = expense;
	
	}
	
	public void setSelectedExpense (int position){
		this.selectedExpense = expenseList.get(position);
	
	}
	
	public ExpenseList getExpenseList() {
		return this.expenseList;
	}
	
	public Expense getSelectedExpense(){
		return this.selectedExpense;
	}
	
	
	private ExpenseController(Claim claim){
		this.expenseList = claim.getExpenseList();
	
	}
	
	public void addExpense(Expense expense){
		this.expenseList.add(expense);
		LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
	}
	
	public void removeExpense(Expense expense){
		this.expenseList.remove(expense);
		LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
	}
	
	public GregorianCalendar getExpenseDate() {
		return this.selectedExpense.getExpenseDate();
	}
	
	public void setExpenseDate(GregorianCalendar calendar) {
		this.selectedExpense.setExpenseDate(calendar);
	}
	
}