package team14.expenseexpress.test;

import team14.expenseexpress.R;
import team14.expenseexpress.activity.ClaimListActivity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class ViewClaimListTest extends ActivityInstrumentationTestCase2<ClaimListActivity> {

	Instrumentation instrumentation;
	ClaimListActivity activity;
	ListView listview;
	
	public ViewClaimListTest() {
		super(ClaimListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
	}
	
	public void testItemsOnList() {
	
		listview = (ListView) activity
				.findViewById(R.id.claimListView);

		assertNotNull("Item not created for question view", listview);

	}


}
