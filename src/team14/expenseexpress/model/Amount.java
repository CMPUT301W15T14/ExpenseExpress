package team14.expenseexpress.model;

import java.util.ArrayList;


/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Amount {
    private double number;
    private Currency currency;

    /**
     * Empty private constructor.
     */
    private Amount(){
        // empty
    }

    /**
     * Getter for numerical amount.
     *
     * @return  Numerical amount.
     */
    public double getNumber() {
        return number;
    }

    /**
     * Constructor intended for the field in an Expense.
     *
     * Ensures the String is in Currency.LIST. Otherwise, leaves the String
     * as the default empty String.
     *
     * @param number    Numerical amount.
     * @param currency  Currency (enum).
     */
    public Amount(double number, Currency currency){
        this.number = number;
        this.currency = currency;
    }

    /**
     * Setter for the numerical amount.
     *
     * @param number    Updated numerical amount.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for the currency as a String.
     *
     * @return  Either an empty string or belongs to Currency.LIST.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Setter for the currency as a String.
     *
     * Only sets the currency if it's in Currency.LIST.
     *
     * @param currency  Currency as a String.
     */
    public void setCurrency(Currency currency) {
            this.currency = currency;
    }

    /**
     * Constructor intended for calculating the amount tallies inside a Claim.
     *
     * Gets the list of expenses from the Claim, totals their Amounts that match the
     * currency param, and sets the object field number to that total amount.
     *
     * @param claim     The Claim requesting a tally.
     * @param currency  The currency this tally is for.
     */
    public Amount(Claim claim, Currency currency){
        ArrayList<Expense> expenses = claim.getExpenseList().getExpenses();
        for (int i = 0; i<expenses.size(); i++){
            if (expenses.get(i).getAmount().getCurrency().equals(currency)){
                number += expenses.get(i).getAmount().getNumber();
            }
        }
    }
}