package team14.expenseexpress.test;

import team14.expenseexpress.activity.ExpenseEditActivity;
import team14.expenseexpress.controller.ReceiptController;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.Receipt;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Test the Receipt model and Receipt Controller to check if we can add a receipt to
 * a expense item
 * Related Use Cases: UC17, UC19 
 */
public class TestReceiptModel extends
		ActivityInstrumentationTestCase2<ExpenseEditActivity> {

	public TestReceiptModel() {
		super(ExpenseEditActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testAddReceipt() {
		/*
		 * Add a receipt to a expense item
		 * Related Use Case: UC17
		 */
		Receipt receipt = new Receipt();
		receipt = null;
		Expense expense = new Expense();
		ReceiptController.getInstance().setSelectedReceipt(receipt);
		expense.setReceipt(ReceiptController.getInstance().getSelectedReceipt());
		assertNull("ReceiptController get and set receipt does not work", expense.getReceipt());
		/*
		 * Edit a receipt
		 * Related Use Case: UC19
		 */
	}
}
