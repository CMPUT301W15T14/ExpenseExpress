import java.util.ArrayList;

import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import android.test.ActivityInstrumentationTestCase2;


public class FilterByTagsTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	public ClaimModel claim;
	public ClaimModel claim2;
	public ClaimModel claim3;
	public ClaimantController controller;
	
	public FilterByTagsTest(){
		super(MainActivity.class);
	}
	
	public void FilerClaimsByTags() {
		
		claim = new ClaimModel();
		claim2 = new ClaimModel();
		claim3 = new ClaimModel();
		
		claim.setClaimantName("Canada");
		claim.setClaimantName("USA");
		claim.setClaimantName("Australia");
		
		controller = new ClaimantController("Bob");
		controller.addClaim(claim);
		controller.addClaim(claim2);
		controller.addClaim(claim3);
		
		controller.setClaim(0);
		controller.addTag("Hello");
		controller.setClaim(1);
		controller.addTag("Hello");
		controller.setClaim(2);
		controller.addTag("GoodBye");
		
		controller.filterTag("Hello");
		ArrayList<ClaimModel> filteredClaims = controller.getFilteredClaims();
		for (int i = 0; i < filteredClaims.size(); i++) {
			ArrayList<String> ClaimTags = filteredClaims.get(i).getTags();
			assertEquals("Tag not equals Hello", ClaimTags.get(0), "Hello");
		}
	}
}
