package team14.expenseexpress.util;

import android.content.Context;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Username;
/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class GsonHelper {
    private Context context;
    public static final String FILENAME_USERNAMES = "usernames.sav";
    public static final String FILENAME_CLAIMS_SUFFIX = "_claims.sav";

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
     * Deserializes usernames.sav into an ArrayList of Usernames and returns it.
     *
     * @return  The Username objects in local memory in an ArrayList.
     */
    public ArrayList<Username> loadUsernames() {
        // TODO
        return null;
    }

    /**
     * Serializes an object using Gson and stores it in the specified filename locally.
     *
     * @param object The ArrayList of Usernames to serialize and store locally.
     */
    public void save(Object object, String filename) {
        // TODO
    }

    public ArrayList<Claim> loadClaims(String filename) {
        // TODO
        return null;
    }
}