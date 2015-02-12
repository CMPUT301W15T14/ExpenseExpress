import java.util.ArrayList;
import java.util.GregorianCalendar;

import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import team14.teamproject.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;


public class UserStory4Test extends
		ActivityInstrumentationTestCase2<AddExpenseActivity> {

	ArrayList<ClaimModel> claims;
	ArrayList<ExpenseModel> expenses;
	ClaimantController controller;
	ClaimModel claim;
	ExpenseModel expense1;
	ExpenseModel expense2;
	GregorianCalendar date1;
	GregorianCalendar date2;
	int amount1;
	int amount2;
	int currency1;
	int currency2;
	int category1;
	int category2;
	String description1;
	String description2;
	String description3;
	
	public US4_1_1Test(){
		super(AddExpenseActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		claims = new ArrayList<ClaimModel>();
		claim = new ClaimModel();
		controller = new ClaimantController("Bob");	
		controller.addClaim(claim);
		controller.setClaim(0);
		controller.setExpense(0);
		date1 = new GregorianCalendar(1999,9,1);
		date2 = new GregorianCalendar(2005,5,5);
		amount1 = 599;
		amount2 = 9000;
		currency1 = 1;
		currency2 = 3;
		category1 = 2;
		category2 = 4;
		description1 = "This is Expense #1";
		description2 = "This is Expense #2";
		description3 = "This is Expense #2, modified description";
		}
	
	/* US4.01.01
	 * As a claimant, I want to make one or more expense items for an expense claim,
	 * each of which has a date the expense was incurred, a category, a textual description,
	 * amount spent, and unit of currency.
	 */
	
	/*
	 * US04.07.01
	 * As a claimant, I want to delete an expense item while changes are allowed.
	 */
	
	public void testAddAndRemoveMultipleExpenses(){
		expenses = claim.getExpenses();
		assertTrue("List of expenses should be empty", claim.getExpenses().size() == 0);
		controller.addExpense(expense1);
		assertTrue("List should have 1", claim.getExpenses().size() == 1);
		controller.addExpense(expense2);
		assertTrue("List should have 2", claim.getExpenses().size() == 2);
		controller.removeExpense(expense2);
		assertTrue("List should have 1", claim.getExpenses().size() == 1);
		controller.removeExpense(expense1);
		assertTrue("List should be empty", claim.getExpenses().size() == 0);
	}
	
	/*
	 * US04.06.01
	 * As a claimant, I want to edit an expense item while changes are allowed.
	 */
	
	public testEditExpense(){
		expenses = claim.getExpenses();
		expenses.clear();
		expenses.add(expense1);
		expenses.add(expense2);
		controller.setExpense(0);
		controller.setDate(date1);
		controller.setAmount(amount1);
		controller.setCurrency(currency1);
		controller.setCategory(category1);
		controller.setDescription(description1);
		controller.setExpense(1);
		controller.setDate(date2);
		controller.setAmount(amount2);
		controller.setCurrency(currency2);
		controller.setCategory(category2);
		controller.setDescription(description2);
		controller.setExpense(0);
		assertEquals("First expense's date should be 1999/9/1 now", controller.getDate()
				,date1);
		controller.setExpense(1);
		assertEquals("Second expense's amount should be 9000 now", controller.getAmount(),
				amount2);
		assertEquals("Second expense's description is 'this is expense #2'", controller.
				getDescription(), description2);
		controller.setDescription(description3);
		assertEquals("Second expense's description is modified'", controller.
				getDescription(), description3);
	}

	/*
	 * US04.02.01
	 * As a claimant, I want the category for an expense item to be one of air fare,
	 * ground transport, vehicle rental, private automobile, fuel, parking, registration,
	 * accommodation, meal, or supplies.
	 */
	
	public void testCategoryList(){
		ExpenseModel expense = new ExpenseModel();
		ArrayList<String> list = expense.getCategories();
		assertTrue("Air fare should be in the list", list.contains("Air fare"));
		assertTrue("Ground transport should be in the list", list.contains("Ground transport"));
		// etc.
	}
	
	public void testCategoryEdit(){
		ExpenseModel expense = new ExpenseModel();
		controller.setExpense(0);
		expenses = controller.getExpenses();
		expenses.clear();
		expenses.add(expense);
		controller.setCategory(3);
		assertEquals("Category should be set to 'Private automobile'", "Private automobile",
				controller.getCategory());
		controller.setCategory(4);
		assertEquals("Category should now be set to 'Fuel'", "Fuel", controller.getCategory());
	}
	
	/*
	 * US04.03.01
	 * As a claimant, I want the currency for an expense amount to be one of CAD, USD,
	 * EUR, GBP, CHF, JPY, or CNY.
	 */
	
	public void testCurrencyList(){
		ExpenseModel expense = new ExpenseModel();
		ArrayList<String> list = expense.getCurrencies();
		assertTrue("CAD be in the list", list.contains("CAD"));
		assertTrue("USD should be in the list", list.contains("USD"));
		// etc.
	}
	
	public void testCategoryEdit(){
		ExpenseModel expense = new ExpenseModel();
		controller.setExpense(0);
		expenses = controller.getExpenses();
		expenses.clear();
		expenses.add(expense);
		controller.setCurrency(3);
		assertEquals("Category should be set to GBP", "GBP",
				controller.getCurrency());
		controller.setCurrency(4);
		assertEquals("Category should now be set to 'CHF'", "CHF", controller.getCurrency());
	}
	
	/*
	 * US04.04.01
	 * As a claimant, I want to manually flag an expense item with an incompleteness
	 * indicator, even if all item fields have values, so that I am reminded that the
	 * item needs further editing.
	 */
	
	public void testFlag(){
		ExpenseModel expense = new ExpenseModel();
		controller.setExpense(0);
		assertFalse("Flag should be false initially", controller.getFlag());
		controller.raiseFlag();
		assertTrue("Flag should be true now", controller.getFlag());
		controller.lowerFlag();
		assertFalse("Flag shoudl be false again", controller.getFlag());
	}
	
	// These are difficult to write JUnit tests for:
	
	/*
	 * US04.05.01
	 * As a claimant, I want to view an expense item and its details.
	 */
	
	/*
	 * US04.08.01
	 * As a claimant, I want the entry of an expense item to have minimal required 
	 * navigation in the user interface.
	 */
}
