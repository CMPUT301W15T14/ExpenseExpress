package team14.expenseexpress;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;


public class ClaimList { //I don't think this works --Skinny
	public ArrayList<Claim> claimList;    
	private static ClaimList instance = null;
	    
	private ClaimList() {
		claimList = new ArrayList<Claim>();
	}
	 
	public static synchronized ClaimList getInstance() {
		if (instance == null) {
			instance = new ClaimList();
		}
		return instance;
	    
	}
}

