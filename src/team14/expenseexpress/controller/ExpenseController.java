package team14.expenseexpress.controller;

import java.util.Collections;
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
	
	  /**
		 * Set Expense recieves fields for a new or edited expense and modifies the Expense object
		 * This ensures a MVC framework.
		 * @param String category,
						    GregorianCalendar expenseDate,
						    double amount,
						    String currency,
						    String description,
						    String name,
						    boolean complete
		 *
		 */
	public void setExpense(String category,
						    GregorianCalendar expenseDate,
						    double amount,
						    String currency,
						    String description,
						    String name,
						    boolean complete){
		selectedExpense.setCategory(category);
		selectedExpense.setExpenseDate(expenseDate);
		selectedExpense.setDescription(description);
		Amount actualAmount = new Amount(amount, Currency.fromString(currency));
		selectedExpense.setAmount(actualAmount);
		selectedExpense.setName(name);
		selectedExpense.setComplete(complete);
		selectedExpense = null;
		sortExpenseList();
		LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
	}
	  /**
		 * SetSelecetedExpense(Expense expense) takes an Expense object and sets it as the selectedExpense variable
		 * 
		 *@param Expense expense
		 */
	public void setSelectedExpense (Expense expense){
		this.selectedExpense = expense;
	
	}
	  /**
		 * SetSelecetedExpense(position) takes a position and gets the expense at that index and sets it as 
		 * setSelectedExpense
		 *@param Expense expense
		 */
	public void setSelectedExpense (int position){
		this.selectedExpense = expenseList.get(position);
	
	}
	
	public void makeSelectedExpense(){
		this.selectedExpense = new Expense();
		addExpense(this.selectedExpense);
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
		sortExpenseList();
		LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
	}
	
	public void removeExpense(Expense expense){
		this.expenseList.remove(expense);
		sortExpenseList();
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
	public void sortExpenseList(){
		expenseList.sort();

	}
}