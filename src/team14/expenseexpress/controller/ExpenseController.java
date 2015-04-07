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
	/**
	 * if instance is null, makes instance equal to a new Expense
	 * @return instance of ExpenseController
	 */
	public static ExpenseController getInstance(){
		if (instance == null){
			instance = new ExpenseController();
		}
		return instance;
	}
	/**
	 * if instance is null, makes instance equal to a new Expense
	 * updates expenseList
	 */
	public static void initialize() {
		if (instance == null){
			instance = new ExpenseController();
		}
		expenseList = ClaimController.getInstance().getSelectedClaim().getExpenseList(); 
	}
	
	  /**
		 * Set Expense receives fields for a new or edited expense and modifies the Expense object
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
						    boolean incomplete){
		selectedExpense.setCategory(category);
		selectedExpense.setExpenseDate(expenseDate);
		selectedExpense.setDescription(description);
		Amount actualAmount = new Amount(amount, Currency.fromString(currency));
		selectedExpense.setAmount(actualAmount);
		selectedExpense.setName(name);
		selectedExpense.setIncomplete(incomplete);
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
	/**
	 * Makes selected Expense the given Expense
	 */
	public void makeSelectedExpense(){
		this.selectedExpense = new Expense();
		addExpense(selectedExpense);
	}
	
	/**
	 * Returns the expense list
	 * @return instance of expenseList
	 */
	public ExpenseList getExpenseList() {
		return this.expenseList;
	}
	/**
	 * Returns selected Expense
	 * @return instance of selected Expense
	 */
	public Expense getSelectedExpense(){
		return this.selectedExpense;
	}
	
	/**
	 * Make's the current claim's expenseList the selected expenseList
	 * @param claim that is in current use by UI
	 */
	private ExpenseController(Claim claim){
		this.expenseList = claim.getExpenseList();
	
	}
	/**
	 * adds expense to current expenseList
	 * @param expense added to expenseList
	 */
	public void addExpense(Expense expense){
		this.expenseList.add(expense);
	}
	/**
	 * removes expense from expenseList
	 * @param expense the expense to be removed
	 */
	public void removeExpense(Expense expense){
		this.expenseList.remove(expense);
		sortExpenseList();
		LocalFileHelper.getInstance().saveClaims(ClaimController.getInstance().getClaimList());
	}
	/**
	 * If no selected expense returns empty GregorianCalendar
	 * @return The the date of the selected Expense
	 */
	public GregorianCalendar getExpenseDate() {
		if (selectedExpense == null)
			return new GregorianCalendar();
		else
			return this.selectedExpense.getExpenseDate();
	}
	/**
	 * Sets expense date to calendar
	 * @param calendar the date that the expense is to be set to
	 */
	public void setExpenseDate(GregorianCalendar calendar) {
		this.selectedExpense.setExpenseDate(calendar);
	}
	/**
	 * Searches through expenses to see if expense is contained in expenseList
	 * @param name name of expense searching for
	 * @return True if name contained in list, False if not
	 */
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
	/**
	 * sorts expenseList
	 */
	public void sortExpenseList(){
		expenseList.sort();

	}
	public void setLatitude(double latitude){
		this.selectedExpense.setLatitude(latitude);
	}
	public double getLatitude(){
		return this.selectedExpense.getLatitude();
	}
	public void setLongitude(double longitude){
		this.selectedExpense.setLongitude(longitude);
	}
	public double getLongitude(){
		return this.selectedExpense.getLatitude();
	}
}