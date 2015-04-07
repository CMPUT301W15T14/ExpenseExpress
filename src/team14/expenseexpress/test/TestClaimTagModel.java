package team14.expenseexpress.test;

import java.util.ArrayList;

import team14.expenseexpress.activity.EditTags;
import team14.expenseexpress.controller.TagListController;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.TagList;
import team14.expenseexpress.model.User;
import android.test.ActivityInstrumentationTestCase2;
/**
 * Tests to check ClaimTag and TagList model and its methods. Also test the TagListController and 
 * any other model related to claim model. 
 * <p>Related Use Cases: UC8
 */
public class TestClaimTagModel extends
		ActivityInstrumentationTestCase2<EditTags> {

	public TestClaimTagModel() {
		super(EditTags.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	/**
	 * Test the ClaimTagModel. If this model works then Managing tags should also work
	 */
	public void testClaimTagModel() {
		
		ClaimTag claimtag = new ClaimTag("1st tag");	
		assertEquals("Claim Tag get name does not work", "1st tag" ,claimtag.getName());
		
		claimtag.setName("renamed tag");
		assertEquals("Claim tag set name does not work", "renamed tag", claimtag.getName());
		
	}
	/**
	 * Test the TagList model and the TagListController to see if we can list, add, rename and delete tags
	 */
	public void testManageTags() {
		UserController.getInstance().setCurrentUser(new User("zach"));
		TagListController.getInstance().initialize();
		ClaimTag tag = new ClaimTag("1st tag");
		TagListController.getInstance().addTag(tag);
		TagList taglist = TagListController.getInstance().getTagList();
		ArrayList<ClaimTag> claimtaglist = taglist.getTags();
		
		assertFalse("Claim tag list does not work", claimtaglist.size() == 0);
		assertEquals("Claim Tag get name does not work", "1st tag" ,claimtaglist.get(0).getName());
		TagListController.getInstance().setTagName(claimtaglist.get(0), "renamed tag");
		assertEquals("Claim tag set name does not work", "renamed tag", claimtaglist.get(0).getName());
		TagListController.getInstance().removeTag(tag);
		assertTrue("delete claim tag does not work", claimtaglist.size() == 0);
		
	}

}
