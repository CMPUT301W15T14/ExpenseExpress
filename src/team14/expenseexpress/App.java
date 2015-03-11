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
	Things this class will hold for the Activities to access:
	
	- The relevant list of Claims (which hold all the Expenses)
	
	- Public methods that deal with all things related to data storage and server query
	(the Activities will call these methods instead of worry about it themselves):
	
	- The mode (Approver/Claimant) for the Activities.
	
	The methods are noted in comments below, for the individual activities.
	
 */
public class App extends Application {
	private User user;
    private ArrayList<Claim> claims;
    private ArrayList<ClaimTag> tags;
    private FileHelper fh;
    private ElasticSearchHelper esh;
    private int mode;
    public static final int CLAIMANT_MODE = 1;
    public static final int APPROVER_MODE = 2;

    public static final String KEY = "( ͡° ͜ʖ ͡°)"; // for Intent putExtra/getExtra

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<ClaimTag> getTags(){
		return tags;
	}
	
	public void saveTags(){
		fh.saveTags(tags);	
	}
	
    @Override
    public void onCreate() {
        super.onCreate();
        fh = FileHelper.getInstance(this);
        esh = ElasticSearchHelper.getInstance(this);
        tags = fh.getTags();
    }

    public void setClaims(){
        this.claims = claims;
    }


    public ArrayList<Claim> getClaims(){
        if (claims == null){
            return new ArrayList<Claim>();
        }
        return claims;
    }

    public void setMode(int mode){
    	if (mode == CLAIMANT_MODE || mode == APPROVER_MODE){
    		this.mode = mode;
    	}
    }
    
	public int getMode() {
		return mode;
	}
}