package team14.expenseexpress;

import android.app.Application;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.FileHelper;
import team14.expenseexpress.util.ElasticSearchHelper;

/**
 * 
	This class is instantiated once, before the launcher activity (LoginActivity).
	This class will exist for the lifetime of the app. All of its fields will outlive individual Activities.
	This allows us to never worry about Intent.putExtra/getExtra when working within the app.
	It allows isolates FileIO and HTTP handling from Controllers so that Controllers only ever deal with
	model classes, not those helper classes.
		
	Things this class provides for the Activities & Controllers to access:
	
	- The relevant list of Claims (which hold all the Expenses)
	
	- Public methods that deal with all things related to data storage and server query
	(the Activities will call these methods via Controller classes as noted below):
	
	- The mode (Approver/Claimant) for the Activities.
	
	The methods are noted in comments below, for the individual activities.
	
 */
public class App extends Application {
	private User user;
    private ArrayList<Claim> localClaims;
    private ArrayList<Claim> remoteClaims;
    
    private ArrayList<ClaimTag> tags;
    private FileHelper fileHelper;
    private ElasticSearchHelper elasticSearchHelper;
    private int mode;
    public static final int CLAIMANT_MODE = 1;
    public static final int APPROVER_MODE = 2;
    
    @Override
    public void onCreate() {
        super.onCreate();
        fileHelper = FileHelper.getInstance(this);
        elasticSearchHelper = ElasticSearchHelper.getInstance(this);
        tags = fileHelper.getTags();
    }

    /**
     * Automatically called in abstract class ExpenseExpressActivity's onCreate.
     * In Activities, just refer to 'user' (it's a member field in the abstract class)
     * 
     * @return
     */
    public User getUser() {
		return user;
	}

    /**
     * Called at the end of LoginActivity.
     * 
     * @param user
     */
	public void setUser(User user) {
		this.user = user;
	}

	
	/**
	 * Called by ClaimController
	 * 
	 */
	public void saveClaimsLocal(ArrayList<Claim> claims){
		fileHelper.saveClaims(claims);
	}
	
	/**
	 * Called by TagController.
	 * 
	 */
	public void saveTags(){
		fileHelper.saveTags(tags);	
	}
	
	/**
	 * Called by TagController.
	 * 
	 * @return
	 */
	public ArrayList<ClaimTag> getTags(){
		return tags;
	}
	
	
    /**
     * Called by ClaimController at the start of ClaimListActivity.
     * 
     */
    public void loadLocalClaims(){
    	switch(mode){
    	case CLAIMANT_MODE:
    		localClaims = fileHelper.getLocalClaimsForClaimant(user);
    		break;
    	case APPROVER_MODE:
    		localClaims = fileHelper.getLocalClaimsForApprover(user);
    		break;
    	}
    }
    
    /**
     * Called by ClaimController at the start of ClaimListActivity.
     * 
     */
    public void loadRemoteClaims(){
    	switch(mode){
    	case CLAIMANT_MODE:
    		remoteClaims = elasticSearchHelper.getRemoteClaimsForClaimant(user);
    		break;
    	case APPROVER_MODE:
    		remoteClaims = elasticSearchHelper.getRemoteClaimsForApprover(user);
    		break;
    	}
    }
    
    public boolean uploadClaim(Claim claim){
    	return elasticSearchHelper.uploadClaim(claim);
    }


    /**
     * Called by ClaimController.
     * 
     */
    public ArrayList<Claim> getLocalClaims(){
        if (localClaims == null){
            return new ArrayList<Claim>();
        }
        return localClaims;
    }
    
    /**
     * Called by ClaimController.
     * 
     */
    public ArrayList<Claim> getRemoteClaims(){
    	if (remoteClaims == null){
    		return new ArrayList<Claim>();
    	}
    	return remoteClaims;
    }

    /**
     * Called at the end of LoginActivity.
     * 
     * @param mode
     */
    public void setMode(int mode){
    	if (mode == CLAIMANT_MODE || mode == APPROVER_MODE){
    		this.mode = mode;
    	}
    }
    
    /**
     * Called by most Activities and Controllers
     * 
     * @return
     */
	public int getMode() {
		return mode;
	}
}