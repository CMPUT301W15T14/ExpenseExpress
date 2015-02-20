package team14.expenseexpress;

import android.app.Application;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Username;
import team14.expenseexpress.util.GsonHelper;
import team14.expenseexpress.util.Synchronizer;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class ExpenseExpressApplication extends Application {
    private ArrayList<Username> usernames;
    private ArrayList<Claim> claims;
    private GsonHelper gsonHelper;
    private Synchronizer synchronizer;
    public boolean connected;

    public static final String KEY = "( ͡° ͜ʖ ͡°)"; // for Intent putExtra/getExtra

    /**
     * Prepares a list of user IDs and their associated names.
     *
     * (this onCreate is called before any Activity has been created)
     */
    @Override
    public void onCreate() {
        super.onCreate();
        gsonHelper = new GsonHelper(this);
        synchronizer = new Synchronizer(this, gsonHelper);
        getUsers();
    }

    /**
     * Gets the most up-to-date list of user IDs and their associated names.
     *
     * Checks for a connection to the server. If there is one, the local and server list of
     * unique user IDs and their names are compared and synchronized. If there isn't
     * a connection, then the list is populated from local storage.
     *
     * @return  The user IDs and their associated names (Username objects) in an ArrayList.
     */
    public ArrayList<Username> getUsers() {
        connected = checkConnection();
        if (connected){
            synchronizer.synchronizeUsernames();
        }
        usernames = gsonHelper.loadUsernames();
        return usernames;
    }

    private boolean checkConnection() {
        // TODO
        return false;
    }

    /**
     * Getter for the user ID and names, intended for LoginActivity.
     *
     * @return  The Usernames in an ArrayList.
     */
    public ArrayList<Username> getUsernames() {
        return usernames;
    }

    /**
     * Setter for the list of Claims that the rest of the Application deals with.
     *
     * This is to be called at the final step of the LoginActivity.
     *
     * @param claims    The relevant list of Claims in an ArrayList.
     */
    public void setClaims(ArrayList<Claim> claims){
        this.claims = claims;
    }

    /**
     * Getter for the list of Claims, set in the LoginActivity at the final step.
     *
     * This should return a specific list of Claims appropriate to the User who logged in. This
     * method is intended to be called throughout the Application.
     *
     * @return  A relevant list of Claims in an ArrayList.
     */
    public ArrayList<Claim> getClaims(){
        if (claims == null){
            return new ArrayList<>();
        }
        return claims;
    }

    public void synchronizeClaims(long id) {
        synchronizer.synchronizeClaims(id);
    }
}