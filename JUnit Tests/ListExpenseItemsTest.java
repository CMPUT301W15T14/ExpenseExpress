package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import team14.teamproject.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;
public class ListExpenseItemsTest extends ActivityInstrumentationTestCase2<ListExpenseItemsAcitivity> {
public ListExpenseItemsTest(){
super(ListExpenseItemsActivity.class);
}
ClaimModel claim;
ClaimModel claim1;
ArrayList<ExpenseModel> expenses;
ExpenseController controller;
ExpenseModel expense;
GregorianCalendar date1 = new GregorianCalendar(2015,1,1);
GregorianCalendar date2 = new GregorianCalendar(2015,1,3);
GregorianCalendar date3 = new GregorianCalendar(2015,1,5);
GregorianCalendar testdate1 = new GregorianCalendar(2015,1,2);
GregorianCalendar testdate2 = new GregorianCalendar(2015,1,3);
GregorianCalendar testdate3 = new GregorianCalendar(2015,1,1);
GregorianCalendar testdate4 = new GregorianCalendar(2015,1,5);
int amount1 = 1;
int amount2 = 2;
int amount3 = 3;
int currency1 = 1;
int currency2 = 2;
int currency3 = 2;
int category1 = 1;
int category2 = 2;
int category3 = 3;
int photo1;
int photo2;
int photo3;
boolean flag1 = true;
boolean flag2 = true;
boolean flag3 = true;
String description1;
String description2;
String description3;
protected void setUp() throws Exception {
super.setUp();
controller = new Controller(claim);
//set up expense data
claim = new ClaimModel();
Expense expense1 = controller.newExpense();
Expense expense2 = controller.newExpense();
Expense expense3 = controller.newExpense();
controller.setExpenseDate(expense1, date1);
controller.setExpenseAmount(expense1, amount1);
controller.setExpenseCategory(expense1, category1);
controller.setExpenseCurrency(expense1, currency1);
controller.setExpenceDesciption(expense1, description1);
controller.setExpencePhoto(expense1, photo1);
controller.setExpenceFlag(expense1, flag1);
expenses.add(expense1);
controller.setExpenseDate(expense2, date2);
controller.setExpenseAmount(expense2, amount2);
controller.setExpenseCategory(expense2, category2);
controller.setExpenseCurrency(expense2, currency2);
controller.setExpenceDesciption(expense2, description2);
controller.setExpencePhoto(expense2, photo2);
controller.setExpenceFlag(expense2, flag2);
expenses.add(expense2);
controller.setExpenseDate(expense3, date3);
controller.setExpenseAmount(expense3, amount3);
controller.setExpenseCategory(expense3, category3);
controller.setExpenseCurrency(expense3, currency3);
controller.setExpenceDesciption(expense3, description3);
controller.setExpencePhoto(expense3, photo3);
controller.setExpenceFlag(expense3, flag3);
expenses.add(expense3);
}
public void testDateCheck(){
//Test a claim in date range testdate1 to testdate2
assertTrue("Date is not in range",testdate2 <= controller.getExpenseDate(expense1) >= testdate1);
claim.addExpense(expense1);
assertTrue("Date is not in range",testdate2 <= controller.getExpenseDate(expense2) >= testdate1);
claim.addExpense(expense2);
assertTrue("Date is not in range",testdate2 <= controller.getExpenseDate(expense3) >= testdate1);
//In this case,expense 1 and expense 2 are in the claim
assertTrue("List should have 2", claim.size() == 2);
//Test a claim in date range testdate3 to testdate4
assertTrue("Date is not in range",testdate4 <= controller.getExpenseDate(expense1) >= testdate3);
claim1.addExpense(expense1);
assertTrue("Date is not in range",testdate4 <= controller.getExpenseDate(expense2) >= testdate3);
claim1.addExpense(expense2);
assertTrue("Date is not in range",testdate4 <= controller.getExpenseDate(expense3) >= testdate3);
claim1.addExpense(expense3);
//In this case,all 3 expenses are in the claim
assertTrue("List should have 2", claim1.size() == 3);
}
public void ViewExpenseItems(){
//claim view expense items
assertEquals("Date has been shown for expense1", date1, claim.getExpenseDate(expense1));
assertEquals("Date has been shown for expense2", date2, claim.getExpenseDate(expense2));
assertEquals("Amount has been shown for expense1", amount1, claim.getExpenseAmount(expense1));
assertEquals("Amount has been shown for expense2", amount2, claim.getExpenseAmount(expense2));
assertEquals("Category has been shown for expense1", category1, claim.getExpenseCategory(expense1));
assertEquals("Category has been shown for expense2", category2, claim.getExpenseCategory(expense2));
assertEquals("Currency has been shown for expense1", currency1, claim.getExpenseCurrency(expense1));
assertEquals("Currency has been shown for expense2", currency2, claim.getExpenseCurrency(expense2));
assertEquals("Description has been shown for expense1",
description1, claim.getExpenseDescription(expense1));
assertEquals("Description has been shown for expense2",
description2, claim.getExpenseDescription(expense2));
assertTure("Photo has not been added for expense1", claim.getExpensePhoto(expense1) == photo1);
assertTure("Photo has not been added for expense2", claim.getExpensePhoto(expense2) == photo2);
assertTure("Expense has not been compelete for expense1", claim.getExpensePhoto(expense1) == flag1);
assertTure("Expense has not been compelete for expense2", claim.getExpensePhoto(expense2) == flag2);
//claim1 view expense items
assertEquals("Date has been shown for expense1", date1, claim.getExpenseDate(expense1));
assertEquals("Date has been shown for expense2", date2, claim.getExpenseDate(expense2));
assertEquals("Date has been shown for expense2", date3, claim.getExpenseDate(expense3));
assertEquals("Amount has been shown for expense1", amount1, claim.getExpenseAmount(expense1));
assertEquals("Amount has been shown for expense2", amount2, claim.getExpenseAmount(expense2));
assertEquals("Amount has been shown for expense2", amount3, claim.getExpenseAmount(expense3));
assertEquals("Category has been shown for expense1", category1, claim.getExpenseCategory(expense1));
assertEquals("Category has been shown for expense2", category2, claim.getExpenseCategory(expense2));
assertEquals("Category has been shown for expense2", category3, claim.getExpenseCategory(expense3));
assertEquals("Currency has been shown for expense1", currency1, claim.getExpenseCurrency(expense1));
assertEquals("Currency has been shown for expense2", currency2, claim.getExpenseCurrency(expense2));
assertEquals("Currency has been shown for expense2", currency3, claim.getExpenseCurrency(expense3));
assertEquals("Description has been shown for expense1",
description1, claim.getExpenseDescription(expense1));
assertEquals("Description has been shown for expense2",
description2, claim.getExpenseDescription(expense2));
assertEquals("Description has been shown for expense3",
description3, claim.getExpenseDescription(expense3));
assertTure("Photo has not been added for expense1", claim.getExpensePhoto(expense1) == photo1);
assertTure("Photo has not been added for expense2", claim.getExpensePhoto(expense2) == photo2);
assertTure("Photo has not been added for expense3", claim.getExpensePhoto(expense3) == photo3);
assertTure("Expense has not been compelete for expense1", claim.getExpensePhoto(expense1) == flag1);
assertTure("Expense has not been compelete for expense2", claim.getExpensePhoto(expense2) == flag2);
assertTure("Expense has not been compelete for expense3", claim.getExpensePhoto(expense2) == flag3);
}
}