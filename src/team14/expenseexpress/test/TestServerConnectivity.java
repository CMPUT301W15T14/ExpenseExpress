package team14.expenseexpress.test;

import java.util.ArrayList;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Test the ElasticSearchHelper class to check the server connection by submitting a claim
 * Related Use Case: UC20
 *
 */
public class TestServerConnectivity extends
		ActivityInstrumentationTestCase2<ClaimListActivity> {

	public TestServerConnectivity() {
		super(ClaimListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testServer() {
		int i;
		User user = new User("Bob");
		Mode.set(1);
		UserController.getInstance().setCurrentUser(user);
		ClaimController.getInstance().initialize(getActivity());
		Claim claim = ClaimController.getInstance().getNewClaim();
		claim.setName("Bob");
		claim.setStatus("Submitted");
		assertEquals("Claim model set status does not work", "Submitted", claim.getStatus());
		ElasticSearchHelper.getInstance().addClaim(claim);
		ArrayList<Claim> claimlist = ElasticSearchHelper.getInstance().getSubmitted();
		assertTrue("ElasticSearchHelper model add claims does not work", claimlist.size() > 0);
		for (i = 0; i < claimlist.size(); i++) {
			if (claimlist.get(i).getName().equals("Bob")) {
				assertEquals("ElasticSearchHelper model submit claim does not work", "Bob", claimlist.get(i).getName());
				break;
			}
		}
		
		//ElasticSearchHelper.getInstance().deleteClaim(claim);
		//assertTrue("ElasticSearchHelper model add claims does not work", claimlist.size() == 0);
	}
}
