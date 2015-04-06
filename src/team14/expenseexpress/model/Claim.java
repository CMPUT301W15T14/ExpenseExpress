package team14.expenseexpress.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import team14.expenseexpress.controller.UserController;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 * 
 * Claim holds 5 Lists:
 * tags, expenseList, destinations, amount, approverComments
 * It is the key object in the Expense Express framework.
 * 
 * 
 * 
 */
public class Claim implements Comparable{
    private final ArrayList<ClaimTag> tags;
    private final ExpenseList expenseList;
    private final ArrayList<Destination> destinations;
    private GregorianCalendar startDate;     // use getDateString for String
    private GregorianCalendar endDate;
    private ArrayList<Amount> amounts;
    private String status;
    private String name;
    private User approver; // the approver responsible for the most recent change in status
    private User claimant;
    private ArrayList<ApproverComment> approverComments;
    private ArrayList<String> totalAmount;
    
    private final long claimId;
    private long lastSave;
    
    public Claim() {
    	this.expenseList = new ExpenseList();
    	GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
    	this.setStartDate(calendar);
	    this.setEndDate(calendar);
	    this.claimId = generateCurrentTime();
	    this.lastSave = generateCurrentTime();
	    this.destinations = new ArrayList<Destination>();
	    this.tags = new ArrayList<ClaimTag>();
	    this.status = Status.IN_PROGRESS;
	    this.totalAmount = new ArrayList<String>();
	    this.claimant = UserController.getInstance().getCurrentUser();
	    this.setApprover(null);
    }

    public void setLastSave() {
    	this.lastSave = generateCurrentTime();
    }
    
    public long getLastSave() {
    	return this.lastSave;
    }
    
    /**
     * Obtains a unique expense id based off of real time in milliseconds.
     * 
     * @return The claim id as a long.
     */
    private long generateCurrentTime(){
    	return System.currentTimeMillis();
    }
	/**
	 * Setter, sets Claim name
	 * 
	 * @param String name
	 *
	 */
    public void setName(String name) {
    	this.name = name;
    }
    /**
	 * getter, gets Claim name
	 * 
	 * @return String name
	 *
	 */
    public String getName() {
    	return this.name;
    }
    /**
	 * getter, gets Claim unique ID
	 * 
	 * @return long
	 *
	 */
    public long getId(){
    	return this.claimId;
    }
    /**
	 * getter, gets ExpenseList
	 * 
	 * @return ArrayList<Expense>
	 *
	 */
    public ExpenseList getExpenseList() {
        return this.expenseList;
    }

    /**
  	 * getter, gets TagList
  	 * 
  	 * @return ArrayList<ClaimTag>
  	 *
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
    
    public void removeTag(ClaimTag tag) {
            tags.remove(tag);
    }

    /**
     * Adds an Expense to this Claim while preventing duplicates.
     *
     * @param expense
     */
    public void addExpense(Expense expense) {
        this.expenseList.add(expense);
    }

    /**
     * Getter for the Destinations associated with this Claim.
     *
     * @return  Destinations (locations and reasons) of this Claim as an ArrayList.
     */
    public ArrayList<Destination> getDestinations() {
        return destinations;
    }
    public ArrayList<String> getDestinationsNames(){
    	ArrayList <String> list = new ArrayList<String>();
    	ArrayList <Destination> des = getDestinations();
    	for (Destination destination : des)
    		list.add(destination.getDestination());
		return list;
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
     * Getter for the starting date associated with this Claim.
     *
     * @return  The starting date, as a GregorianCalendar object.
     */
    public GregorianCalendar getStartDate() {
        return this.startDate;
    }
    
    /**
     * Getter for the end date associated with this Claim.
     *
     * @return  The starting date, as a GregorianCalendar object.
     */
    public GregorianCalendar getEndDate() {
        return this.endDate;
    }
    

    /**
     * Returns the starting date formatted to a String, in "short" form (e.g. 12/5/1)
     *
     * @return  The starting date, as a String in the short date format.
     */
    public String getStartDateString(){
    	if (startDate == null){
    		return "Date not set";
    	}
        return DateFormat.getDateInstance(DateFormat.SHORT).format(startDate.getTime());
    }

    /**
     * Returns the starting date formatted to a String of the specified format.
     *
     * @param format    One of DateFormat.DEFAULT,FULL,LONG,MEDIUM,SHORT.
     * @return          The starting date, as a String, in the requested format.
     */
    public String getStartDateString(int format){
        return DateFormat.getDateInstance(format).format(startDate.getTime());
    }
    
    
    /**
     * Returns the end date formatted to a String, in "short" form (e.g. 12/5/1)
     *
     * @return  The starting date, as a String in the short date format.
     */
    public String getEndDateString(){
        return DateFormat.getDateInstance(DateFormat.SHORT).format(endDate.getTime());
    }

    /**
     * Returns the end date formatted to a String of the specified format.
     *
     * @param format    One of DateFormat.DEFAULT,FULL,LONG,MEDIUM,SHORT.
     * @return          The starting date, as a String, in the requested format.
     */
    public String getEndDateString(int format){
        return DateFormat.getDateInstance(format).format(endDate.getTime());
    }
    

    /**
     * Setter for the starting date.
     *
     * @param date  The starting date as a GregorianCalendar object.
     */
    public void setStartDate(GregorianCalendar date) {
        this.startDate = date;
    }
    
    /**
     * Setter for the end date.
     *
     * @param date  The starting date as a GregorianCalendar object.
     */
    public void setEndDate(GregorianCalendar date) {
        this.endDate = date;
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




    public void setApproverComments(ArrayList<ApproverComment> approverComments) {
        this.approverComments = approverComments;
    }


	public User getClaimant() {
		// TODO Auto-generated method stub
		return claimant;
	}
	
	
	/**
	 * Comparator used to sort Claims.
	 * 
	 * @author pkuczera
	 *
	 */
	@SuppressWarnings("rawtypes")
	public static class ClaimComparator implements Comparator {
		@Override
		public int compare(Object lhs, Object rhs) {
			if(!(lhs instanceof Claim) || !(rhs instanceof Claim)) 
				throw new ClassCastException();
			Claim c1 = (Claim)lhs;
			Claim c2 = (Claim)rhs;
			if(c1.getStartDate().equals(c2.getStartDate())) {
				return (c1.getId() > c2.getId()) ? 1 : -1;
			}
			return c1.getStartDate().compareTo(c2.getStartDate());
				}
		}
	/**
	 * date To string used to get formatted date objects
	 * 
	 * @author pkuczera
	 * @return String date
	 */
	
	public String startDateToString() {
		if(startDate == null) {
			return "";
		}
		return (String) android.text.format.DateFormat.format("yyyy-MM-dd",this.startDate);
	}
	/**
	 * date To string used to get formatted date objects
	 * 
	 * @author pkuczera
	 * @return String date
	 */
	public String endDateToString() {
		if(endDate == null) {
			return "";
		}
		return (String) android.text.format.DateFormat.format("yyyy-MM-dd",this.endDate);
	}
	
	public void setTotalAmounts(ArrayList<String> amount) {
		this.totalAmount = amount;
	}
	
	public ArrayList<String> getTotalAmounts() {
		return totalAmount;
	}

	@Override
	public int compareTo(Object another) {
		Claim otherClaim = (Claim) another;
		
		return this.startDate.compareTo(otherClaim.startDate);
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}
	
}