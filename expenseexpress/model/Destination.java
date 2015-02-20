package team14.expenseexpress.model;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Destination {
    private String destination;
    private String reason;

    /**
     * Constructor that initializes the destination String.
     *
     * @param destination   The destination as a String.
     */
    public Destination(String destination){
        this.destination = destination;
    }

    /**
     * Getter for the destination String.
     *
     * @return  The destination as a String.
     */
    public String getDestination() {
        return destination;
    }


    /**
     * Setter for the destination String.
     *
     * @param destination   The new destination String to replace the old one.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Getter for the reason associated with the destination.
     *
     * @return  The reason as a String.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Setter for the reason associated with the destination.
     *
     * @param reason    The new reason String to replace the old one.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}