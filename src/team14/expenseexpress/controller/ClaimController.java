package team14.expenseexpress.controller;

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
	private ClaimList submittedList;
	private Claim selectedClaim;
	
	// singleton
	private static ClaimController instance = null;
	private ClaimController(){
		this.selectedClaim = new Claim();
	}
	/**
	 * Returns the current Claim instance
	 * @return Instance of ClaimController
	 */
	public static ClaimController getInstance(){
		if (instance == null){
			instance = new ClaimController();
		}
		return instance;
	}
	/**
	 * returns the ClaimList
	 * @return instance of ClaimList
	 */
	public ClaimList getClaimList() {
		return this.claimList;
	}
	
	/**
	 * Sets given Claim as current Claim
	 * @param claim is the given claim
	 */
	public void setSelectedClaim (Claim claim){
			this.selectedClaim = claim;
	}
	/**
	 * Returns given Claim
	 * @return Instance of given Claim
	 */
	public Claim getSelectedClaim(){
		return this.selectedClaim;
	}
	/**
	 * Sets given context to current instance
	 * @param context Context
	 */
	public void initialize(Context context){
		this.context = context;
		if(Mode.get() == Mode.APPROVER) {
			this.submittedList = new ClaimList();
		}
		loadLocalClaims();
	}
	/**
	 * Adds given claim to ClaimList
	 * @param claim is the given claim
	 */
	public void addClaim(Claim claim){
		claimList.add(claim);
		LocalFileHelper.getInstance().saveClaims(claimList);
	}
	/**
	 * Removes given claim from ClaimList
	 * @param claim is the given claim
	 */
	public void removeClaim(Claim claim){
		claimList.remove(claim);
		LocalFileHelper.getInstance().saveClaims(claimList);
	}
	/**
	 * Sorts and saves ClaimList
	 */
	public void saveClaims() {
		LocalFileHelper.getInstance().saveClaims(claimList);
	}
	/**
	 * Creates new claim and returns it
	 * @return The new claim
	 */
	public Claim getNewClaim(){
		Claim claim = new Claim();
		return claim;
	}
	/**
	 * Gets instance of ClaimList from last save
	 */
	private void loadLocalClaims() {
		this.claimList = LocalFileHelper.getInstance(context).loadClaims();
	}
	public ClaimList getSubmittedList() {
		return submittedList;
	}
	public void setSubmittedList(ClaimList submittedList) {
		this.submittedList = submittedList;
	}
}

