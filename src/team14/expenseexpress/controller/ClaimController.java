package team14.expenseexpress.controller;

import android.content.Context;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.util.LocalFileHelper;
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
	private static ClaimController instance;
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
		//initializeClaimList();
		loadLocalClaims();
	}
	
	public void addClaim(Claim claim){
		claimList.add(claim);
	}
	
	public void removeClaim(Claim claim){
		claimList.remove(claim);
	}
	
	public Claim getNewClaim(){
		Claim claim = new Claim();
		return claim;
	}
	/*
	private void initializeClaimList() {
		
		claimList = new ClaimList();
		ClaimList localClaims = loadLocalClaims();
		ClaimList remoteClaims = loadRemoteClaims();
		merge(localClaims, remoteClaims);
	}

	private void merge(ClaimList localClaims, ClaimList remoteClaims) {
		// TODO merge the two lists.
		List<Claim> mergedList = new ArrayList<Claim>(localClaims);
		for (int i = 0; i < remoteClaims.size(); i++){
			Claim remoteClaim = remoteClaims.get(i);
			if (mergedList.contains(remoteClaim)){
				mergedList.remove(remoteClaim);
			}
			mergedList.add(remoteClaims.get(i));
		}
	}

	private List<Claim> loadRemoteClaims() {
		ElasticSearchHelper helper = ElasticSearchHelper.getInstance(context);
		List<Claim> remoteClaims = new ArrayList<Claim>();
		switch (Mode.get()){
		case Mode.APPROVER:
			remoteClaims = helper.getRemoteClaimsForApprover(user);
			break;
		case Mode.CLAIMANT:
			remoteClaims = helper.getRemoteClaimsForClaimant(user);
		}
		return remoteClaims;
	}


*/
	private void loadLocalClaims() {
		this.claimList = LocalFileHelper.getInstance(context).loadClaims();
	}

	public void removeExpense(Expense expense) {
		selectedClaim.remove(expense);
		
	}
}

