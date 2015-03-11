package team14.expenseexpress.util;

import android.content.Context;

import java.util.ArrayList;

import team14.expenseexpress.App;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Username;

public class ElasticSearchHelper {
    private Context context;
    private FileHelper fh;
    
    private static ElasticSearchHelper instance;
    
    private ElasticSearchHelper(Context context){
    	this.context = context;
    	this.fh = FileHelper.getInstance(context);
    }
    
    public static ElasticSearchHelper getInstance(Context context){
    	if (instance == null){
    		instance = new ElasticSearchHelper(context);
    	}
    	return instance;
    }
    
    public ArrayList<Claim> getClaimsAsApprover(long approverId){
    	// TODO: for Approver mode, from server, get a list of all the claims that don't have the user's ID
    	return null;
    }
    
    public boolean uploadClaims(ArrayList<Claim> claims){
    	// TODO: push a list of claims to the server (maybe return whether it succeeded)
    	return false;
    }

}