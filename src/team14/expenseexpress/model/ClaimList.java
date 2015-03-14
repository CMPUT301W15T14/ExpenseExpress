package team14.expenseexpress.model;

import java.util.ArrayList;
import java.util.Collections;

public class ClaimList {
	
	// Singleton, to persist across Activities
	private static ClaimList instance;
	
	private ClaimList(){
		claimList = new ArrayList<Claim>();
	}
	
	public static ClaimList getInstance(){
		if (instance == null){
			instance = new ClaimList();
		}
		return instance;
	}
	
	private ArrayList<Claim> claimList = null;
	
	
	public ArrayList<Claim> getClaims() {
		return this.claimList;
	}
	
	public void addClaim(Claim newClaim) {
		this.claimList.add(newClaim);
	}
	
	public void removeClaim(Claim oldClaim) {
		this.claimList.remove(oldClaim);
	}
	
	public int size() {
		return this.claimList.size();
	}
	
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(claimList, new Claim.ClaimComparator());
	}
	
}
