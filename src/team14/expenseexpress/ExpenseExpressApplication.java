package team14.expenseexpress;

import android.app.Application;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.FileHelper;
import team14.expenseexpress.util.GsonHelper;
import team14.expenseexpress.util.ElasticSearchHelper;


public class ExpenseExpressApplication extends Application {
	private User user;
    private ArrayList<Claim> claims;
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

    @Override
    public void onCreate() {
        super.onCreate();
        fh = FileHelper.getInstance(this);
        esh = ElasticSearchHelper.getInstance(this);
    }

    public void setClaims(ArrayList<Claim> claims){
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