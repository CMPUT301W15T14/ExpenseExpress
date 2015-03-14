package team14.expenseexpress.controller;

import java.util.List;

import android.content.Context;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.FileHelper;
/**
 * Singleton controller.
 * 
 * Roles:
 * - Initialize ClaimList singleton for Activities.
 * - Modifies the same ClaimList singleton in response to the following user input:
 * 		1. Adding a new Claim. 
 * 		2. Deleting a Claim. 
 * 		3. Referencing a Claim.
 * 
 * - Does NOT modify fields inside Claim (done by ClaimController).
 * - Does NOT deal with tags (done by TagListController for list, and ClaimController for attachment to a Claim).
 * 
 */
public class ClaimListController {
	private Context context;
	private ClaimList claimList;
	
	// singleton
	private static ClaimListController instance;
	private ClaimListController(){
	
	}
	
	public ClaimListController getInstance(){
		if (instance == null){
			instance = new ClaimListController();
		}
		return instance;
	}
	
	
	
	public void initialize(Context context){
		this.context = context;
		initializeClaimList();
	}

	private void initializeClaimList() {
		claimList = ClaimList.getInstance();
		claimList.clear();
		FileHelper fileHelper = FileHelper.getInstance(context);
		switch (Mode.get()){
		
		}
		List<Claim> localClaims = fileHelper.getLocalClaims();
	}
	
	
	
	
	
	
	
	
	
	/*
	private static ClaimList claimList = null;
	
	public static ClaimList getClaimList(User user) {
		if (claimList == null) {
			try {
				claimList = FileHelper.getHelper().getLocalClaimsForClaimant(user);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	*/

}