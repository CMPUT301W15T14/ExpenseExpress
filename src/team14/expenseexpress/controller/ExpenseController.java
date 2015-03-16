package team14.expenseexpress.controller;

import java.util.GregorianCalendar;

import android.content.Context;
import team14.expenseexpress.model.Amount;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Currency;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;
import team14.expenseexpress.model.Receipt;
import team14.expenseexpress.util.LocalFileHelper;


public class ExpenseController {
	
	private static ExpenseList expenseList;
	private Expense selectedExpense = null; 
	
	//singleton
	private static ExpenseController instance = null;
	
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
						    String name,
						    boolean complete){
		if (selectedExpense != null){
			selectedExpense.setCategory(category);
		    selectedExpense.setExpenseDate(expenseDate);
			selectedExpense.setDescription(description);
			selectedExpense.setReceipt(receipt);
			Amount actualAmount = new Amount(amount, Currency.fromString(currency));
			selectedExpense.setAmount(actualAmount);
			selectedExpense.setName(name);
			selectedExpense.setComplete(complete);
		}
		else{
			Expense expense = new Expense();
			expense.setCategory(category);
		    expense.setExpenseDate(expenseDate);
			expense.setDescription(description);
			expense.setReceipt(receipt);
			Amount actualAmount = new Amount(amount, Currency.fromString(currency));
			expense.setAmount(actualAmount);
			expense.setName(name);
			expense.setComplete(complete);
			this.expenseList.add(expense);
			}
		selectedExpense = null;
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
		if (selectedExpense == null)
			return new GregorianCalendar();
		else
			return this.selectedExpense.getExpenseDate();
	}
	
	public void setExpenseDate(GregorianCalendar calendar) {
		this.selectedExpense.setExpenseDate(calendar);
	}
	public boolean containsByName(String name){
		if (selectedExpense != null){
			for (Expense expense : expenseList.getExpenses()){
				if ((expense.getName().equals(name)) && (expense.getId() != selectedExpense.getId()))
						return true;
			}
		}
		else{
			for (Expense expense : expenseList.getExpenses()){
				if (expense.getName().equals(name))
						return true;
			}
		}
		return false;
	}
}