package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import team14.teamproject.ApproverController;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import team14.teamproject.ExpenseModel;
import android.test.ActivityInstrumentationTestCase2;
public class ReturnClaimTest extends
ActivityInstrumentationTestCase2<MainActivity> {
public Claim claim;
public Controller controller;
public Approver approver;
public ReturnClaimTest(){
super(MainActivity.class);
}
public returnedClaimTest() {
claim = new Claim();
controller = new Controller("Bob");
approver = new Approver("Joe");
controller.addClaim(claim);
approver.setStatus(claim, "Returned")
approver.setApproverComment("Returned Claim");
assertEquals("Status not equal Returned",controller.getStatus(), "Returned");
assertEquals("No approver name", controller.getApproverName(), "Joe");
asserEquals("No approver comment", controller.getComment(), "Returned Claim");
}
}