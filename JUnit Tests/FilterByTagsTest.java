package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import android.test.ActivityInstrumentationTestCase2;
public class FilterByTagsTest extends
ActivityInstrumentationTestCase2<MainActivity> {
public Claim claim;
public Claim claim2;
public Claim claim3;
public Controller controller;
public FilterByTagsTest(){
super(MainActivity.class);
}
public void FilerClaimsByTags() {
claim = new Claim();
claim2 = new Claim();
claim3 = new Claim();
claim.setClaimantName("Canada");
claim.setClaimantName("USA");
claim.setClaimantName("Australia");
controller = new Controller("Bob");
controller.addClaim(claim);
controller.addClaim(claim2);
controller.addClaim(claim3);
controller.addTag(claim, "Hello");
controller.addTag(claim2, "Hello");
controller.addTag(claim3, "GoodBye");
controller.filterTag("Hello");
ArrayList<Claim> filteredClaims = controller.getFilteredClaims();
for (int i = 0; i < filteredClaims.size(); i++) {
ArrayList<String> ClaimTags = filteredClaims.get(i).getTags();
assertEquals("Tag not equals Hello", ClaimTags.get(0), "Hello");
}
}
}