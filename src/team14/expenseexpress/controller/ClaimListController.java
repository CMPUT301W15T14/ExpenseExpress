package team14.expenseexpress.controller;

import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.FileHelper;

public class ClaimListController {
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
}
