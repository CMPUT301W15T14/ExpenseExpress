package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.GregorianCalendar;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;
public class OffLineTest extends ActivityInstrumentationTestCase2<OffLineActivity> {
private static final boolean False = false;
Controller controller;
GregorianCalendar date1 = new GregorianCalendar(2015,1,1);
GregorianCalendar date2 = new GregorianCalendar(2015,1,2);
int amount1 = 100;
int amount2 = 200;
int currency1 = 11;
int currency2 = 222;
int category1 = 1;
int category2 = 2;
String description1 = "aaaaa";
String description2 = "aaaaa";
boolean connection1 = false;
boolean connection2 = true;
boolean push1 = false;
boolean push2 = true;
public OffLineTest(){
super(OffLineActivity.class);
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
controller.setExpenceConnection(expense, connection1);
expenses.add(expense);
}
public void testOffLineEditExpense(){
// Edit expense
controller.setExpenseDate(expense, date2);
controller.setExpenseAmount(expense, amount2);
controller.setExpenseCategory(expense, category2);
controller.setExpenseCurrency(expense, currency2);
controller.setExpenceDesciption(expense, description2);
// Change values while offline
assertEquals("Date has been changed for expense", date2, controller.getExpenseDate(expense));
assertEquals("Amount has been changed for expense", amount2, controller.getExpenseAmount(expense));
assertEquals("Category has been changed for expense", category2, controller.getExpenseCategory(expense));
assertEquals("Currency has been changed for expense", currency2, controller.getExpenseCurrency(expense));
assertEquals("Description has been changed for expense",
description2, controller.getExpenseDescription(expense));
assertEquals("Internet has been diabled", connection2, controller.getExpenseDate(expense));
//The connection is not true,set push to false.stop pushing it
controller.setExpencePush(expense,push1);
}
public void testOnLineEditExpense(){
// Edit expense
controller.setExpenseDate(expense, date2);
controller.setExpenseAmount(expense, amount2);
controller.setExpenseCategory(expense, category2);
controller.setExpenseCurrency(expense, currency2);
controller.setExpenceDesciption(expense, description2);
// Change values while online
assertEquals("Date has been changed for expense", date2, controller.getExpenseDate(expense));
assertEquals("Amount has been changed for expense", amount2, controller.getExpenseAmount(expense));
assertEquals("Category has been changed for expense", category2, controller.getExpenseCategory(expense));
assertEquals("Currency has been changed for expense", currency2, controller.getExpenseCurrency(expense));
assertEquals("Description has been changed for expense",
description2, controller.getExpenseDescription(expense));
assertEquals("Internet has been diabled", connection2, controller.getExpenseDate(expense));
//The connection is true,set push to false.push it TO server
controller.setExpencePush(expense,push2);
}
}