package team14.expenseexpress.test;

import java.util.ArrayList;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.LocalFileHelper;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Test the LocalFileHelper class to check if data can be saved locally using gson
 * and if we can load those claims
 * Related Use case: UC5
 */
public class TestDataSaved extends ActivityInstrumentationTestCase2<ClaimListActivity> {

	
	public TestDataSaved() {
		super(ClaimListActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}
	
	public void testSaveData(){
		User user = new User("Bob1");
		Mode.set(Mode.CLAIMANT);
		UserController.getInstance().setCurrentUser(user);
		ClaimController.getInstance().initialize(getActivity());
		Claim claim = new Claim();
		claim.setName("Bob");
		ClaimController.getInstance().addClaim(claim);
		ArrayList<Claim> claims = LocalFileHelper.getInstance().loadClaims().getClaims();
		assertEquals("LocalFileHelper loadClaims does not work", "Bob", claims.get(0).getName());
	}
	
}
