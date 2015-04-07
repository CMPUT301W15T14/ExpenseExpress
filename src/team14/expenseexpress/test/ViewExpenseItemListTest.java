package team14.expenseexpress.test;

import team14.expenseexpress.R;
import team14.expenseexpress.activity.ExpenseListActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
/**
 * Test to check if we can see the Expense list on both the claimant side and approver side
 * Related Use Case: UC16, UC25
 *
 *
 */
public class ViewExpenseItemListTest extends
		ActivityInstrumentationTestCase2<ExpenseListActivity> {

	public ViewExpenseItemListTest() {
		super(ExpenseListActivity.class);
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
				.findViewById(R.id.ExpenseList);

		assertNotNull("Item not created for question view", listview);

	}

}
