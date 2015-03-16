package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import android.test.ActivityInstrumentationTestCase2;
public class GiveTagsTest extends
ActivityInstrumentationTestCase2<EditClaimsActivity> {
public ClaimModel claim;
public ClaimantController controller;
public GiveTagsTest(){
super(EditClaimsActivity.class);
}
public void GiveClaimsTags() {
claim = new ClaimModel();
controller = new ClaimantController("Bob");
controller.addClaim(claim);
controller.setClaim(0);
controller.addTag("Hello");
controller.addTag("Goodbye");
ArrayList<String> ClaimTags = controller.getTags();
assertEquals("Tag not equals Hello", ClaimTags.get(0), "Hello");
assertEquals("Tag not equals Goodbye", ClaimTags.get(1), "Goodbye");
}
}