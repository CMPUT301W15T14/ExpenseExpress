// Note: this is for Use Case 4.2

import java.util.ArrayList;
import java.util.GregorianCalendar;
import team14.expenseexpress.ClaimModel;
import team14.expenseexpress.Controller;
import team14.expenseexpress.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;


public class ExpenseCategorTest extends
		ActivityInstrumentationTestCase2<ExpenseActivity> {

    private Controller controller;
    private ExpenseModel expense;
    private ClaimModel claim;
    private final int category = 1;
	
	public AddIniializeRemoveMultipleExpensesTest(){
		super(AddExpenseActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		// Mock controller initialized with a reference to the claim
		claim = new ClaimModel();
	    controller = new Controller(claim);
		// Preconditions: an expense item is initialized aleady
		expense = controller.newExpense();
	    ArrayList<ExpenseModel> expenses = claim.getExpenses();
	    expenses.add(expense);
	}
	        
	
	public void testSetCategory(){
	    int index = expenses.indexOf(expense);
	    /* Mock controller :
	        public void setExpenseCategory(index, category){
	            ExpenseModel expense = expenses.get(index);
	            expense.setCategory(category);
	        }
	    */
	    controller.setExpenseCategory(index, category);
	    assertEquals("The category has been successfully set", controller.getExpenseCategory(index), category);
	    // Pretend: static ArrayList<Integer> sCategories holds user-preset categories, in the ExpenseModel class
	    // An alternative implementation could be a hash or an ArrayList<String> where the category int is its index
	    assertTrue("The category is one from a preset list", ExpenseModel.sCategories.contains(Integer(category)));
	}
}

