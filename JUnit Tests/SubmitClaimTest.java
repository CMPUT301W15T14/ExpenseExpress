package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import team14.teamproject.ApproverController;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import team14.teamproject.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;
public class SubmitClaimTest extends
ActivityInstrumentationTestCase2<MainActivity> {
public Claim claim;
public Controller controller;
public Approver approver;
public Expense expense1;
ArrayList<Expense> expenses;
public SubmitClaimTest(){
super(MainActivity.class);
}
public void submittedClaimTest() {
expense1 = new Expense();
claim = new Claim();
controller = new Controller("Bob");
//test to check if the program recognize an incomplete claim
controller.addClaim(claim);
controller.setStatus("submitted");
assertFalse("incomplete", controller.completed());
//test to check if a completed claim's status can change to submit
controller.addDestination("Canada");
controller.setReason("Canada", "I love Snow");
controller.setStartDate(1, 1, 2000);
controller.setEndDate(1, 2, 2000);
ArrayList<expense> expenses = claim.getExpenses();
expenses.add(expense1);
controller.setStatus("submitted");
assertEquals("Status not equal submitted",controller.getStatus(), "submitted");
}
}