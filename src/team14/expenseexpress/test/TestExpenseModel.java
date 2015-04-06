package team14.expenseexpress.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import team14.expenseexpress.activity.ExpenseListActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.model.Amount;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Currency;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.Receipt;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Tests to check Expense and ExpenseList model and its methods. Also test the ExpenseController and
 * any other models related to expense model.
 * <p>Related Use Cases: UC10, UC11, UC12, UC13, UC15, UC17
 * 
 *
 */
public class TestExpenseModel extends
		ActivityInstrumentationTestCase2<ExpenseListActivity> {
	
	
	public TestExpenseModel() {
		super(ExpenseListActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
		
	/**
	 * Test the Amount model if this passes then adding a new amount to a expense should
	 * also pass
	 */
	public void testAmountModel() {
		double price = 3.00;
		Currency currency = Currency.fromString("CAD");
		Amount amount = new Amount(price, currency);

		assertEquals("Amount model getting price does not work", price, amount.getNumber());
		assertEquals("Amount model getting currency does not work", currency, amount.getCurrency());
	}
	
	/**
	 * Test the Expense and ExpenseList model and its controller to check if we can add a new expense
	 * with all its attributes set and check if we can delete that expense. Also check if we can edit
	 * the expense added
	 */
	public void testNewExpense() {
		GregorianCalendar date = new GregorianCalendar(15, 3, 16);
		double amount = 3.00;
		Receipt receipt = new Receipt();
		
		Claim claim = new Claim();
		ClaimController.getInstance().setSelectedClaim(claim);
		ExpenseController.initialize();
		ExpenseController.getInstance().setSelectedExpense(null);
		
		/* Test adding a Expense Item
		 * Related Use cases: UC10, UC11. UC12, UC13
		 */
		ExpenseController.getInstance().setExpense("Food", date, amount, "CAD", "I like food", "poutine", false);
		
		
		ArrayList<Expense> expenselist = ExpenseController.getInstance().getExpenseList().getExpenses();
		assertFalse("expensecontrol size", expenselist.size() == 0);
		assertFalse("ExpenseList model and ExpenseController adding new expense does not work", expenselist.size() == 0);
		assertEquals("ExpenseList model and ExpenseController getting expense name does not work",
				"poutine", expenselist.get(0).getName());
		assertEquals("ExpenseList model and ExpenseController getting expense name does not work",
				"Food", expenselist.get(0).getCategory());
		assertEquals("ExpenseList model and ExpenseController getting expense date does not work",
				date, expenselist.get(0).getExpenseDate());
		assertEquals("ExpenseList model and ExpenseController getting expense amount does not work",
				amount, expenselist.get(0).getAmount().getNumber());
		assertFalse("ExpenseList model and ExpenseController check if incomplete does not work", expenselist.get(0).getIncomplete());
		//assertNotNull("ExpenseList model and ExpenseController getting reciept does not work", expenselist.get(0).getReceipt());

		/*
		 * Test editing a Expense Item
		 * Related Use cases: UC15
		 */
		GregorianCalendar newdate = new GregorianCalendar(16, 4, 16);
		double newamount = 4.00;
		
		ExpenseController.getInstance().setSelectedExpense(expenselist.get(0));
		ExpenseController.getInstance().setExpense("airfare", newdate, newamount, "USD", "I like flying", "plane", true);
		
		expenselist = ExpenseController.getInstance().getExpenseList().getExpenses();
		assertFalse("new expense added, did not edit existing expense", ExpenseController.getInstance().getExpenseList().size() > 1);
		assertEquals("ExpenseList model and ExpenseController editing expense name does not work",
				"plane", expenselist.get(0).getName());
		assertEquals("ExpenseList model and ExpenseController editing expense name does not work",
				"airfare", expenselist.get(0).getCategory());
		assertEquals("ExpenseList model and ExpenseController editing expense date does not work",
				newdate, expenselist.get(0).getExpenseDate());
		assertEquals("ExpenseList model and ExpenseController editing expense amount does not work",
				newamount, expenselist.get(0).getAmount().getNumber());
		assertTrue("ExpenseList model and ExpenseController check if incomplete, does not work", expenselist.get(0).getIncomplete());
		//assertNotNull("ExpenseList model and ExpenseController editing reciept does not work", expenselist.get(0).getReceipt());
		
		//Test Delete Expense Item
		Expense expense = ExpenseController.getInstance().getSelectedExpense();
		ExpenseController.getInstance().removeExpense(expense);
		expenselist = ExpenseController.getInstance().getExpenseList().getExpenses();
		assertTrue("ExpenseList model and ExpenseController deleteing expense does not work", expenselist.size() == 0);
	}

}
