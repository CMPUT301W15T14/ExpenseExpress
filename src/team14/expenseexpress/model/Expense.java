package team14.expenseexpress.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;


/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Expense implements Cloneable {
    private String category;
    private GregorianCalendar expenseDate;
    private Amount amount;
    private String description;
    private Receipt receipt;
    private String name;
    private boolean incomplete; 


    private long expenseId;
    /**
     * Getter for the Unique ID associated with this Expense.
     * returns the unique ID
     * @return long Id
     */
    public long getId(){
    	return this.expenseId;
    }
    /**
     * Getter for the complete boolean associated with this Expense.
     * returns the completeness of the expense, as indicated by user
     * @return  Boolean complete
     */
    public boolean getIncomplete(){
    	return this.incomplete;
    }
    /**
     * Setter for the complete boolean associated with this Expense.
     * Sets the completeness of the expense, as indicated by user
     * 
     * @param Boolean
     */
    public void setIncomplete(boolean incomplete){
    	this.incomplete = incomplete;
    }
    /**
     * Getter for the Amount object associated with this Expense.
     *
     * @return  The cost of this Expense as an Amount object.
     */
    public Amount getAmount() {
        return amount;
    }
    /**
     * Setter for the Amount of this Expense
     *
     * @param amount The Amount object
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    /**
     * Getter for the Expense Date object associated with this Expense.
     * 
     * @return This expense date as a GregorianCalendar object.
     */
    public GregorianCalendar getExpenseDate() {
    	return this.expenseDate;
    }
    
    /**
     * Setter for the Expense Date object associated with this Expense.
     * returns the completeness of the expense, as indicated by user
     * @param GregorianCalendar calendar
     */
    
    public void setExpenseDate(GregorianCalendar calendar) {
    	this.expenseDate = calendar;
    }
    
    /**
     * Default constructor, initializes GregorianCalendar and Amount objects.
     */
    public Expense(){
        this.expenseDate = new GregorianCalendar();
        amount = new Amount(0, null);
        this.expenseId = getExpenseId();
    }
    
    
    /**
     * Obtains a unique expense id based off of real time in milliseconds.
     * text
     * @return The expense id as a long.
     */
    private long getExpenseId() {
    	return System.currentTimeMillis();
    }

    /**
     * Getter for the category of this Expense.
     *
     * @return  The category as a String.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for the category of this Expense, ensuring it is in Category.LIST.
     *
     * @param category  The category as a String (choose from Category constants)
     */
    public void setCategory(String category) {
    	this.category = category;
    }

    /**
     * Getter for the description of this Expense.
     *
     * @return  The description as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of this Expense.
     *
     * @param description   The new description String to replace the old one.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the Receipt object associated with this Expense.
     *
     * @return  The Receipt object.
     */
    public Receipt getReceipt() {
        return receipt;
    }

    /**
     * Setter for the Receipt object associated with this Expense.
     *
     * @param receipt   The Receipt object.
     */
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    /**
     * Private constructor to aid the clone() method.
     * @param category      The Category as a String.
     * @param date          Intended for a shallow copy of the GregorianCalendar object.
     * @param amount        Intended for a shallow copy of the Amount object.
     * @param description   The description as a String.
     */
    private Expense(String name,String category, GregorianCalendar date, Amount amount, String description){
    	this.name = name;
        this.category = category;
        this.expenseDate = date;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Clones this Expense for user convenience (but excludes the receipt for practicality).
     *
     * @return A shallow copy of this Expense (leaves out the Receipt).
     */
    @Override
    public Expense clone(){
        return new Expense(name ,category, (GregorianCalendar) expenseDate.clone(),
                new Amount(amount.getNumber(), amount.getCurrency()), description);
    }
    
    
    /**
     * Comparator used to sort Expenses.
     * 
     * @author pkuczera
     *
     */
    @SuppressWarnings("rawtypes")
	static class ExpenseComparator implements Comparator {
		@Override
		public int compare(Object lhs, Object rhs) {
			if(!(lhs instanceof Expense) || !(rhs instanceof Expense)) 
				throw new ClassCastException();
			Expense e1 = (Expense)lhs;
			Expense e2 = (Expense)rhs;
			
			return (e1.getId() < e2.getId()) ? 1 : -1;
				}
		}

    /**
     * Getter for the Name associated with this Expense.
     * returns the name of the expense
     * @return  String name
     */
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	  /**
     * Setter for the name associated with this Expense.
     * 
     * @param  String name
     */
	public void setName(String name) {
		this.name = name;
		
	}
	
	
    
}