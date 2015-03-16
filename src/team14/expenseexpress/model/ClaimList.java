package team14.expenseexpress.model;

import java.util.ArrayList;
import java.util.Collections;

public class ClaimList {
	
	private ArrayList<Claim> claimList;
	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
	}
	
	public ArrayList<Claim> getClaims() {
		return this.claimList;
	}
	
	public void add(Claim newClaim) {
		this.claimList.add(newClaim);
	}
	
	public void remove(Claim oldClaim) {
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
