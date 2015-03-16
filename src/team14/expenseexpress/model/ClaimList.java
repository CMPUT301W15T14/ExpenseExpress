package team14.expenseexpress.model;

import java.util.ArrayList;
import java.util.Collections;
/**
	 * ClaimList is a List of Claims, that is modified by the ClaimController
	 * 
	 * 
	 *
	 */
public class ClaimList {
	
	private ArrayList<Claim> claimList;
	 /**
     * Constructor creates new ClaimList
     *	
     * 
     */
	public ClaimList(){
		claimList = new ArrayList<Claim>();
	}
	 /**
     * method adds Expense Object to ArrayList<Expense>
     *	
     * @param Expense expense
     */
	public ArrayList<Claim> getClaims() {
		return this.claimList;
	}
	 /**
     * method adds Claim Object to ArrayList<Claim>
     *	
     * @param Claim claim
     */
	public void add(Claim newClaim) {
		this.claimList.add(newClaim);
	}
	 /**
     * method removes Expense Object from ArrayList<Expense>
     *	
     * @param Claim claim
     */
	public void remove(Claim oldClaim) {
		this.claimList.remove(oldClaim);
	}
	 /**
     * method returns size of ArrayList<Expense>
     *	
     * @return int size
     */
	public int size() {
		return this.claimList.size();
	}
	 /**
     * sort ArrayList<Expense>
     *	using pre-built comparator
     * 
     */
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(claimList, new Claim.ClaimComparator());
	}
}
