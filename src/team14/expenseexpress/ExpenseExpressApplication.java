package team14.expenseexpress;

import android.app.Application;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.User;
import team14.expenseexpress.model.Username;
import team14.expenseexpress.util.GsonHelper;
import team14.expenseexpress.util.ElasticSearchHelper;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class ExpenseExpressApplication extends Application {
	private User user;
    private ArrayList<Claim> claims;

    public static final String KEY = "( ͡° ͜ʖ ͡°)"; // for Intent putExtra/getExtra

    
    /**
     * Getter for User (set at the end of the login step).
     * 
     * @return	the User.
     */
    public User getUser() {
		return user;
	}


    /**
     * Setter for the User.
     * 
     * @param user	The User, either pulled from the server or the local one.
     */
	public void setUser(User user) {
		this.user = user;
	}


    @Override
    public void onCreate() {
        super.onCreate();
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
            return new ArrayList<Claim>();
        }
        return claims;
    }
}