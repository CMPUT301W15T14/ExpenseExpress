package team14.expenseexpress.util;

import android.content.Context;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.User;
import team14.expenseexpress.model.Username;
/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class GsonHelper {
    private Context context;
    public static final String SUFFIX = ".sav";

    /**
     * Empty private constructor
     */
    private GsonHelper(){
        // empty
    }

    /**
     * Constructor initializing the Context with the Application context.
     *
     * @param context   The Application context.
     */
    public GsonHelper(Context context){
        this.context = context;
    }


    /**
     * Serializes an object using Gson and stores it in the specified filename locally.
     *
     * @param object The ArrayList of Usernames to serialize and store locally.
     */
    public void save(Object object, String filename) {
        // TODO
    }

    public User loadUser(long id) {
    	String filename = id + SUFFIX;
        // TODO
        return null;
    }
}