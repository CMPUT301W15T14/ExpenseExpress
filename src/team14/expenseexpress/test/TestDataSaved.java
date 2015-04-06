package team14.expenseexpress.test;

import java.util.ArrayList;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.User;
import team14.expenseexpress.util.LocalFileHelper;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
/**
 * Test the LocalFileHelper class to check if data can be saved locally using gson
 * and if we can load those claims
 * Related Use case: UC5
 */
public class TestDataSaved extends ActivityInstrumentationTestCase2<ClaimListActivity> {
	Instrumentation instrumentation;
	ClaimListActivity activity;
	
	public TestDataSaved() {
		super(ClaimListActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
	}
	
	public void testSaveData(){
		ClaimController.getInstance().initialize(activity);
		Claim claim = new Claim();
		User user = new User("Bob");
		Mode.set(1);
		claim.setName("Bob");
		UserController.getInstance().setCurrentUser(user);
		ClaimController.getInstance().addClaim(claim);
		ArrayList<Claim> claims = LocalFileHelper.getInstance().loadClaims().getClaims();
		assertEquals("LocalFileHelper loadClaims does not work", "Bob", claims.get(0).getName());
	}
	
}
