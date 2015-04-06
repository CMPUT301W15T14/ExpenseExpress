package team14.expenseexpress.test;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.test.ActivityInstrumentationTestCase2;

public class TestServerConnectivity extends
		ActivityInstrumentationTestCase2<ClaimListActivity> {

	public TestServerConnectivity() {
		super(ClaimListActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testServer() {
		User user = new User("Bob");
		Mode.set(1);
		UserController.getInstance().addUser(user);
		UserController.getInstance().setCurrentUser(user);
		ClaimController.getInstance().initialize(getActivity());
		Claim claim = ClaimController.getInstance().getNewClaim();
		claim.setName("Bob");
		claim.setStatus("Submitted");
		assertEquals("Claim model set status does not work", "Submitted", claim.getStatus());
		ClaimController.getInstance().addClaim(claim);
		ClaimList claimlist = ClaimController.getInstance().getClaimList();
		ElasticSearchHelper.getInstance(getActivity()).saveRemoteClaimList(claimlist);
		ClaimList claimlist2 = ElasticSearchHelper.getInstance(getActivity()).getRemoteClaimList();
		assertTrue("ElasticSearchHelper model get claims does not work", claimlist2.size() > 0);
		assertEquals("ElasticSearchHelper model save claims does not work", "Bob", claimlist2.getClaims().get(0).getName());
	}
}
