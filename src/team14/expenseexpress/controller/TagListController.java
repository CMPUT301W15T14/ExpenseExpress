package team14.expenseexpress.controller;

import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.TagList;
import android.content.Context;

/**
 * Singleton controller for tags.
 * 
 */
public class TagListController {
	
	private TagList tagList;
	
	// singleton
	private static TagListController instance;
	
	private TagListController(Context context){
		tagList = TagList.getInstance(context);
	}
	
	public static TagListController getInstance(Context context){
		if (instance == null){
			instance = new TagListController(context);
		}
		return instance;
	}
	
	public void addTag(ClaimTag tag){
		tagList.add(tag);
	}
	
	public void removeTag(ClaimTag tag){
		tagList.remove(tag);
	}
	
	public void setTagName(ClaimTag tag, String name){
		tag.setName(name);
	}
}
