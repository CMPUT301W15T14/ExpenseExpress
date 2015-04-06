package team14.expenseexpress.model;
/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Status {
    public static final String APPROVED = "Approved";
    public static final String IN_PROGRESS = "In progress";
    public static final String SUBMITTED = "Submitted";
    public static final String RETURNED = "Returned";
    public static final String[] LIST = {APPROVED, IN_PROGRESS, SUBMITTED, RETURNED};

    public static final String COMPLETE = "Complete"; // for Claimant
    public static final String INCOMPLETE = "Incomplete"; // for Claimant

    /**
     * Empty private constructor
     */
    private Status(){
        // empty
    }
}