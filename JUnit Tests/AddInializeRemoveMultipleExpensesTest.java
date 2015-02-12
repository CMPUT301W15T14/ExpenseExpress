import java.util.ArrayList;
import java.util.GregorianCalendar;

import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import team14.teamproject.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;


public class AddInializeRemoveMultipleExpensesTest extends
		ActivityInstrumentationTestCase2<AddExpenseActivity> {

    private Controller controller;
	final GregorianCalendar date1 = new GregorianCalendar(1980,8,8);
	final GregorianCalendar date2 = new GregorianCalendar(2012,12,12);
	final int amount1 = 5;
	final int amount2 = 10;
	final int currency1 = 1;
	final int currency2 = 2;
	final int category1 = 3;
	final int category2 = 4;
	final String description1 = "Desc1";
	final String description2 = "Desc2";
	final String description3 = "Desc3, which replaced description2";
	
	public US4_1_1Test(){
		super(AddExpenseActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		// Preconditions: a claim has been inialized with no associated expenses.
	        claim = new ClaimModel();
	        expenses = claim.getExpenses();
	        
	        // Mock controller initialized with a reference to the claim
	        controller = new Controller(claim);
	
	public void testAddAndRemoveMultipleExpenses(){
	    Expense expense1 = controller.newExpense();
	    Expense expense2 = controller.newExpense();
		assertTrue("List of expenses should be empty", expenses.size() == 0);
		controller.addExpense(expense1);
		assertTrue("List should have 1", expenses.size() == 1);
		controller.addExpense(expense2);
		assertTrue("List should have 2", expenses.size() == 2);
		controller.removeExpense(expense2);
		assertTrue("List should have 1", expenses.size() == 1);
		controller.removeExpense(expense1);
		assertTrue("List should be empty", expenses.size() == 0);
	}
	
	public void testInitializeExpenses(){
	    expenses.clear();
	    // inialize expense fields with actual values for more than one expense
	    Expense expense1 = controller.newExpense();
	    controller.setExpenseDate(expense1, date1);
	    controller.setExpenseAmount(expense1, amount1);
	    controller.setExpenseCategory(expense1, category1);
	    controller.setExpenseCurrency(expense1, currency1);
	    controller.setExpenceDesciption(expense1, description1);
	    expenses.add(expense1);
	    
	    Expense expense2 = controller.newExpense();
	    controller.setExpenseDate(expense2, date2);
	    controller.setExpenseAmount(expense2, amount2);
	    controller.setExpenseCategory(expense2, category2);
	    controller.setExpenseCurrency(expense2, currency2);
	    controller.setExpenceDesciption(expense2, description2);
	    expenses.add(expense2);
	    
	    // Now test if the values have really been both set
	    AssertEquals("Date has been set properly for expense1", date1, controller.getExpenseDate(expense1));
	    AssertEquals("Date has been set properly for expense2", date2, controller.getExpenseDate(expense2));
	    AssertEquals("Amount has been set properly for expense1", amount1, controller.getExpenseAmount(expense1));
	    AssertEquals("Amount has been set properly for expense2", amount2, controller.getExpenseAmount(expense2));
	    AssertEquals("Category has been set properly for expense1", category1, controller.getExpenseCategory(expense1));
	    AssertEquals("Category has been set properly for expense2", category2, controller.getExpenseCategory(expense2));
	    AssertEquals("Currency has been set properly for expense1", currency1, controller.getExpenseCurrency(expense1));
	    AssertEquals("Currency has been set properly for expense2", currency2, controller.getExpenseCurrency(expense2));
	    AssertEquals("Description has been set properly for expense1", 
	            description1, controller.getExpenseDescription(expense1));
	    AssertEquals("Description has been set properly for expense2",
	            description2, controller.getExpenseDescription(expense2));
	}

}
