package team14.expenseexpress.controller;

import java.util.Collections;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.util.LocalFileHelper;
import android.content.Context;
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

public class ClaimController {
	private Context context;
	private ClaimList claimList;
	private Claim selectedClaim;
	
	// singleton
	private static ClaimController instance = null;
	private ClaimController(){
		this.selectedClaim = new Claim();
	}
	
	public static ClaimController getInstance(){
		if (instance == null){
			instance = new ClaimController();
		}
		return instance;
	}
	
	public ClaimList getClaimList() {
		return this.claimList;
	}
	

	public void setSelectedClaim (Claim claim){
			this.selectedClaim = claim;
	}
	
	public Claim getSelectedClaim(){
		return this.selectedClaim;
	}

	public void initialize(Context context){
		this.context = context;
		loadLocalClaims();
	}
	
	public void addClaim(Claim claim){
		claimList.add(claim);
		LocalFileHelper.getInstance().saveClaims(claimList);
	}
	
	public void removeClaim(Claim claim){
		claimList.remove(claim);
		LocalFileHelper.getInstance().saveClaims(claimList);
	}
	
	public void saveClaims() {
		Collections.sort(claimList.getClaims());
		LocalFileHelper.getInstance().saveClaims(claimList);
	}
	
	public Claim getNewClaim(){
		Claim claim = new Claim();
		return claim;
	}

	private void loadLocalClaims() {
		this.claimList = LocalFileHelper.getInstance(context).loadClaims();
	}

}

