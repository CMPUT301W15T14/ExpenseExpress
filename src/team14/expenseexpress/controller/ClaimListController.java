package team14.expenseexpress.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.ElasticSearchHelper;
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
public class ClaimListController {
	private Context context;
	private ClaimList claimList;
	private User user;
	private Claim selectedClaim;
	
	// singleton
	private static ClaimListController instance;
	private ClaimListController(){
	
	}
	
	public static ClaimListController getInstance(){
		if (instance == null){
			instance = new ClaimListController();
		}
		return instance;
	}
	

	private void setSelectedClaim (Claim claim){
			selectedClaim = claim;
		
	}
	public Claim getSelectedClaim(){
		return selectedClaim;
	}

	public void initialize(Context context){
		this.context = context;
		this.user = User.getInstance();
		initializeClaimList();
	}
	
	public void addClaim(Claim claim){
		claimList.add(claim);
	}
	
	public void removeClaim(Claim claim){
		claimList.remove(claim);
	}
	
	private void initializeClaimList() {
		claimList = ClaimList.getInstance();
		claimList.clear();
		List<Claim> localClaims = loadLocalClaims();
		List<Claim> remoteClaims = loadRemoteClaims();
		merge(localClaims, remoteClaims);
	}

	private void merge(List<Claim> localClaims, List<Claim> remoteClaims) {
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
		List<Claim> remoteClaims;
		switch (Mode.get()){
		case Mode.APPROVER:
			remoteClaims = helper.getRemoteClaimsForApprover(user);
			break;
		case Mode.CLAIMANT:
			remoteClaims = helper.getRemoteClaimsForClaimant(user);
		}
		return remoteClaims;
	}

	private List<Claim> loadLocalClaims() {
		LocalFileHelper fileHelper = LocalFileHelper.getInstance(context);
		List<Claim> localClaims;
		switch (Mode.get()){
		case Mode.APPROVER:
			localClaims = fileHelper.getLocalClaimsForApprover(user);
			break;
		case Mode.CLAIMANT:
			localClaims = fileHelper.getLocalClaimsForClaimant(user);
		}
		return localClaims;
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