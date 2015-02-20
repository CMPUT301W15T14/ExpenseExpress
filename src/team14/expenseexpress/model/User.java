package team14.expenseexpress.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class User {
    private long id;
    public static final String LOCAL_FILE = -1;
    private String name;
    private ArrayList<Claim> claims;
    private GregorianCalendar timestamp;

    public User(String name){
        this.name = name;
        this.id = ;
    }

    /**
     * Getter for the Username object associated with this User.
     *
     * @return  The Username object which holds ID information.
     */
    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    /**
     * Getter for the Claims associated with this User (whether Claimant or Approver).
     *
     * @return  The Claims in an ArrayList.
     */
    public ArrayList<Claim> getClaims() {
        return claims;
    }

    /**
     * Setter for the Claims associated with this User (whether Claimant or Approver).
     *
     * @param claims    The Claims in an ArrayList.
     */
    public void setClaims(ArrayList<Claim> claims){
        this.claims = claims;
    }
}