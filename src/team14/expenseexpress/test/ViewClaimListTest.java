package team14.expenseexpress.test;

import team14.expenseexpress.R;
import team14.expenseexpress.activity.ClaimListActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
/**
 * Test to check if we can see the claim list on both the claimant side and approver side
 * Related Use Case: UC6, UC23
 *
 */
public class ViewClaimListTest extends
		ActivityInstrumentationTestCase2<ClaimListActivity> {

	public ViewClaimListTest() {
		super(ClaimListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	/*
	 * Test both claimant side and approver side
	 */
	public void testItemsOnList() {
		
		ListView listview = (ListView) getActivity()
				.findViewById(R.id.claimListView);

		assertNotNull("Item not created for question view", listview);

	}

}
