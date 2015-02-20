package team14.expenseexpress.model;

import java.util.ArrayList;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class User {
    private Username username;
    private ArrayList<Claim> claims;

    /**
     * Constructor that initializes a Username object with a name. Used by LoginActivity
     * in offline mode.
     *
     * @param name
     */
    public User(String name){
        username = new Username(name);
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