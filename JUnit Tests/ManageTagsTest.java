import java.util.ArrayList;

import team14.teamproject.ClaimModel;
import team14.teamproject.ClaimantController;
import android.test.ActivityInstrumentationTestCase2;


public class ManageTagsTest extends 
		ActivityInstrumentationTestCase2<ManageTagActivity> {

	public TagModel Tags;
	
	public ManageTagsTest(){
		super(ManageTagActivity.class);
	}
	
	public void ManageTags() {
		
		//Add Tag
		Tags.addTag("Hello");
		ArrayList<String> TagList = Tags.getTags();
		assertEquals("Tag not equals", TagList.get(0), "hello");
		
		//Rename Tag
		Tags.renameTag(0, "Goodbye");
		ArrayList<String> TagList = Tags.getTags();
		assertEquals("Tag not equals, did not rename", TagList.get(0), "Goobbye");
		
		//Delete Tag
		Tags.deleteTag(0);
		ArrayList<String> TagList = Tags.getTags();
		assertTrue("Tag did not delete", TagList.isEmpty());
	}
	
}
