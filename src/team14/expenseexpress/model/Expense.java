package team14.expenseexpress.model;

import java.util.Arrays;
import java.util.GregorianCalendar;


/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Expense implements Cloneable, Comparable<Expense>{
    private String category;
    private GregorianCalendar date;
    private Amount amount;
    private String description;
    private Receipt receipt;

    /**
     * Getter for the Amount object associated with this Expense.
     *
     * @return  The cost of this Expense as an Amount object.
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Default constructor, initializes GregorianCalendar and Amount objects.
     */
    public Expense(){
        date = new GregorianCalendar();
        amount = new Amount(0, null);
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
        if (Arrays.asList(Category.LIST).contains(category)) {
            this.category = category;
        }
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
    private Expense(String category, GregorianCalendar date, Amount amount, String description){
        this.category = category;
        this.date = date;
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
        return new Expense(category, (GregorianCalendar) date.clone(),
                new Amount(amount.getNumber(), amount.getCurrency()), description);
    }

    /**
     * Compares two Expenses so they are sortable by date.
     *
     * @param another   The Expense to compare to.
     * @return          An int used by Collections to sort multiple Expenses by date.
     */
    @Override
    public int compareTo(Expense another) {
        return date.compareTo(another.date);
    }
}