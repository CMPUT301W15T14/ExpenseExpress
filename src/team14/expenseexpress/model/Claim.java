package team14.expenseexpress.model;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import team14.expenseexpress.model.constants.Currency;
import team14.expenseexpress.model.constants.Status;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Claim implements Comparable<Claim>{
    private ArrayList<ClaimTag> tags;
    private ArrayList<Expense> expenses;
    private ArrayList<Destination> destinations;
    private GregorianCalendar date;     // use getDateString for String
    private ArrayList<Amount> amounts;
    private String status;
    private long lastApproverId; // the approver responsible for the most recent change in status
    private long claimantId;
    private ArrayList<ApproverComment> approverComments;

    /**
     * Getter for the Expenses, as an ArrayList.
     *
     * @return  The Expenses associated with this Claim, in an ArrayList.
     */
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Getter for the ClaimTags, a s an ArrayList.
     *
     * @return  The ClaimTags associated with this Claim, in an ArrayList.
     */
    public ArrayList<ClaimTag> getTags() {
        return tags;
    }

    /**
     * Adds a ClaimTag to this Claim while preventing duplicates.
     *
     * @param tag   The ClaimTag to add to this Claim.
     */
    public void addTag(ClaimTag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Adds an Expense to this Claim while preventing duplicates.
     *
     * @param expense
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Getter for the Destinations associated with this Claim.
     *
     * @return  Destinations (locations and reasons) of this Claim as an ArrayList.
     */
    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    /**
     * Adds a Destination to this Claim.
     *
     * @param destination   New Destination to add to the Claim.
     */
    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    /**
     * Getter for the date associated with this Claim, i.e. the starting date.
     *
     * @return  The starting date, as a GregorianCalendar object.
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * Returns the starting date formatted to a String, in "short" form (e.g. 12/5/1)
     *
     * @return  The starting date, as a String in the short date format.
     */
    public String getDateString(){
        return DateFormat.getDateInstance(DateFormat.SHORT).format(date.getTime());
    }

    /**
     * Returns the starting date formatted to a String of the specified format.
     *
     * @param format    One of DateFormat.DEFAULT,FULL,LONG,MEDIUM,SHORT.
     * @return          The starting date, as a String, in the requested format.
     */
    public String getDateString(int format){
        return DateFormat.getDateInstance(format).format(date.getTime());
    }

    /**
     * Setter for the starting date.
     *
     * @param date  The starting date as a GregorianCalendar object.
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    /**
     * Instantiates an Amount object for each of the Currency.LIST currencies and returns them
     * as an ArrayList.
     *
     * These represent up-to-date totals calculated by the Amount constructor, based on
     * the Expenses associated with this Claim.
     *
     * @return  The Amount for each Currency.LIST currency in an ArrayList.
     */
    public ArrayList<Amount> getAmounts() {
        amounts = new ArrayList<Amount>();
        for (Currency currency: Currency.values()){
            amounts.add(new Amount(this, currency));
        }
        return amounts;
    }

    /**
     * Getter for the status associated with this Claim.
     *
     * @return  The status as a String.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the status associated with this Claim, ensuring it is in Status.LIST.
     *
     * @param status    The status as a String.
     */
    public void setStatus(String status) {
        if (Arrays.asList(Status.LIST).contains(status)) {
            this.status = status;
        }
    }


    /**
     * Getter for the ApproverComments inside of an ArrayList.
     *
     * @return  The ApproverComments associated with this Claim, in an ArrayList.
     */
    public ArrayList<ApproverComment> getApproverComments() {
        return approverComments;
    }


    /**
     * Getter for the unique ID of the most recent Approver.
     *
     * @return  Unique ID of the Approver, as a long.
     */
    public long getLastApproverId() {
        return lastApproverId;
    }

    /**
     * Setter for the unique ID of the most recent Approver.
     *
     * @param lastApproverId    Unique ID of the Approver, as a long.
     */
    public void setLastApproverId(long lastApproverId) {
        this.lastApproverId = lastApproverId;
    }

    public void setApproverComments(ArrayList<ApproverComment> approverComments) {
        this.approverComments = approverComments;
    }

    /**
     * Getter for the unique Claimant ID associated with this Claim.
     *
     * @return  The claimant ID as a long.
     */
    public long getClaimantId() {
        return claimantId;
    }


    /**
     * Compares two Claims so they are sortable by date.
     *
     * @param another   The Claim to compare to.
     * @return          An int used by Collections to sort multiple Claims by date.
     */
    @Override
    public int compareTo(Claim another) {
        return date.compareTo(another.getDate());
    }
}