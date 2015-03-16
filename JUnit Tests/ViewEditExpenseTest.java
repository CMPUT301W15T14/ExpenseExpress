package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import team14.expenseexpress.ClaimModel;
import team14.expenseexpress.Controller;
import team14.expenseexpress.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;
public class ViewEditExpenseTest extends ActivityInstrumentationTestCase2<ExpenseActivity> {
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
public ViewEditExpenseTest(){
super(ExpenseActivity.class);
}
protected void setUp() throws Exception {
super.setUp();
// Mock controller initialized with a reference to the claim
controller = new Controller(claim);
// Preconditions: a claim has been inialized and includes an initialized expense associated.
claim = new ClaimModel();
expenses = claim.getExpenses();
expense = controller.newExpense();
controller.setExpenseDate(expense, date1);
controller.setExpenseAmount(expense, amount1);
controller.setExpenseCategory(expense, category1);
controller.setExpenseCurrency(expense, currency1);
controller.setExpenceDesciption(expense, description1);
expenses.add(expense);
}
public void testViewExpense(){
AssertEquals("Date can be retrieved for expense", date1, controller.getExpenseDate(expense));
AssertEquals("Amount can be retrieved for expense", amount1, controller.getExpenseAmount(expense));
AssertEquals("Category can be retrieved for expense", category1, controller.getExpenseCategory(expense));
AssertEquals("Currency can be retrieved for expense", currency1, controller.getExpenseCurrency(expense));
AssertEquals("Description can be retrieved for expense",
description1, controller.getExpenseDescription(expense));
}
public void testEditExpense(){
// Edit the fields
controller.setExpenseDate(expense, date2);
controller.setExpenseAmount(expense, amount2);
controller.setExpenseCategory(expense, category2);
controller.setExpenseCurrency(expense, currency2);
controller.setExpenceDesciption(expense, description2);
// Now test if the values have really been set
AssertEquals("Date has been set properly for expense", date2, controller.getExpenseDate(expense));
AssertEquals("Amount has been set properly for expense", amount2, controller.getExpenseAmount(expense));
AssertEquals("Category has been set properly for expense", category2, controller.getExpenseCategory(expense));
AssertEquals("Currency has been set properly for expense", currency2, controller.getExpenseCurrency(expense));
AssertEquals("Description has been set properly for expense",
description2, controller.getExpenseDescription(expense));
}
}