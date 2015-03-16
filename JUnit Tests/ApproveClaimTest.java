package team14.expenseexpress.tests;

import team14.teamproject.ApproverController;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import android.test.ActivityInstrumentationTestCase2;
public class ApproveClaimTest extends
ActivityInstrumentationTestCase2<MainActivity> {
public Claim claim;
public Controller controller;
public Approver approver;
public ApproveClaimTest(){
super(MainActivity.class);
}
public approvedClaimTest() {
claim = new Claim();
controller = new Controller("Bob");
approver = new Approver("Joe");
controller.addClaim(claim);
Approver.setStatus(claim, "Approved");
Approver.setApproverComment("Approved Claim");
assertEquals("Status not equal Returned",controller.getStatus(), "Approved");
assertEquals("No approver name", controller.getApproverName(), "Joe");
asserEquals("No approver comment", controller.getComment(), "Approved Claim");
}
}