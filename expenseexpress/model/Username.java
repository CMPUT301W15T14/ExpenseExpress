package team14.expenseexpress.model;

import java.util.GregorianCalendar;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Username implements Comparable<Username>{

    public static final long NOT_IN_LIST = -1L;
    public static final long LOCAL_ONLY = -2L;
    private String name;
    private GregorianCalendar timestamp;
    private long id;

    /**
     * Constructor that initializes the name and timestamps the object.
     *
     * @param name  The name as a String.
     */
    public Username(String name){
        this.name = name;
        id = LOCAL_ONLY;
        timestamp = new GregorianCalendar();
    }

    /**
     * Getter for the user's name.
     *
     * @return  The name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the user's name which also timestamps it.
     *
     * @param name  The new name String.
     */
    public void setName(String name) {
        this.name = name;
        timestamp = new GregorianCalendar();
    }

    /**
     * Getter for the unique ID of this User.
     *
     * @return  The unique ID as a long.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for the timestamp of this object.
     *
     * @return  The timestamp as a GregorianCalendar object.
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Setter for the unique ID of this user.
     *
     * Only call this method when initializing user information in the web server.
     *
     * @param id    The unique ID assigned to this user, as a long.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     *
     * @param another
     * @return
     */
    @Override
    public int compareTo(Username another) {
        return timestamp.compareTo(another.getTimestamp());
    }
}