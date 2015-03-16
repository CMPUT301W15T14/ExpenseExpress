package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import team14.expenseexpress.ClaimModel;
import team14.expenseexpress.Controller;
import team14.expenseexpress.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;
public class ExpenseCurrencyTest extends
ActivityInstrumentationTestCase2<ExpenseActivity> {
private Controller controller;
private ExpenseModel expense;
private ClaimModel claim;
private final int currency = 1;
public ExpenseCurrencyTest(){
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
public void testSetCategory(){
int index = expenses.indexOf(expense);
/* Mock controller :
public void setExpenseCurrency(index, currency){
ExpenseModel expense = expenses.get(index);
expense.setCurrency(currency);
}
*/
controller.setExpenseCurrency(index, currency);
assertEquals("The currency has been successfully set", controller.getExpenseCurrency(index), currency);
// Pretend: static ArrayList<Integer> sCurrencies holds predefined currencies, in the ExpenseModel class
// An alternative implementation could be a hash or an ArrayList<String> where the currency int is its index
assertTrue("The currency is one from a preset list", ExpenseModel.sCurrencies.contains(Integer(currency)));
}
}