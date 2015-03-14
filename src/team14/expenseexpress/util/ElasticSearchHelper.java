package team14.expenseexpress.util;

import android.content.Context;

import java.util.ArrayList;

import team14.expenseexpress.App;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.User;

public class ElasticSearchHelper {
    private Context context;
    private LocalFileHelper fh;
    
    private static ElasticSearchHelper instance;
    
    private ElasticSearchHelper(Context context){
    	this.context = context;
    	this.fh = LocalFileHelper.getInstance(context);
    }
    
    public static ElasticSearchHelper getInstance(Context context){
    	if (instance == null){
    		instance = new ElasticSearchHelper(context);
    	}
    	return instance;
    }
    
    
/*
 * 	TODO:
 * 
 *  Idea:
 *  
 *  On a background thread, download literally every claim that matters
 *  (=user for claimant, !=user for approver). Add a button in the ClaimsList to "refresh"
 *  
 *  
 */
	public ArrayList<Claim> getRemoteClaimsForClaimant(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Claim> getRemoteClaimsForApprover(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean uploadClaim(Claim claim) {
		// TODO Auto-generated method stub
		return false; // return whether succeeded
	}

}