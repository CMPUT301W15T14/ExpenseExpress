package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import team14.expenseexpress.ClaimModel;
import team14.expenseexpress.Controller;
import team14.expenseexpress.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;
public class IncompletenessFlagTest extends
ActivityInstrumentationTestCase2<ExpenseActivity> {
private Controller controller;
private ExpenseModel expense;
private ClaimModel claim;
public IncompletenessFlagTest(){
super(ExpenseActivity.class);
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
// Mock method: flips the flag boolean of the expense repesenting an UI button
public void testFlipFlag(){
assertFalse("The flag hasn't been raised yet", controller.getFlag(expense));
controller.flipFlag(expense);
assertTrue("The flag has been raised", conroller.getFlag(expense));
controller.flipFlag(expense);
assertFalse("The flag is removed upon user clicking the button again", controller.getFlag(expense));
}
}