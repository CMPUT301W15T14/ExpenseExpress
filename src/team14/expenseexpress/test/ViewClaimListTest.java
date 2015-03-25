package team14.expenseexpress.test;

import team14.expenseexpress.R;
import team14.expenseexpress.activity.ClaimListActivity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.ListView;

public class ViewClaimListTest extends ActivityInstrumentationTestCase2<ClaimListActivity> {

	public ViewClaimListTest() {
		super(ClaimListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Instrumentation instrumentation = getInstrumentation();
		ClaimListActivity activity;
		ActivityMonitor monitor; // this monitors any newly opened activities
	}
	
	public void testItemsOnList() {
		ClaimListActivity activity = getActivity();
		ListView ClaimListView;

		ClaimListView = (ListView) activity
				.findViewById(R.id.claimListView);

		// Assert that none of the views are null

		assertNotNull("Item not created for question view", ClaimListView);

		View mainView = activity.getWindow().getDecorView()
				.findViewById(android.R.id.content);

		// Assert that all of the views are displayed on screen
		assertNotNull(mainView);
		ViewAsserts.assertOnScreen(mainView, ClaimListView);

	}


}
