package team14.expenseexpress.controller;

import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.TagList;
import team14.expenseexpress.util.LocalFileHelper;
import android.content.Context;

/**
 * Singleton controller for tags.
 * 
 */
public class TagListController {
	
	private TagList usersTags;
	private TagList chosenTags;
	private Context context;
	
	// singleton
	private static TagListController instance;
	private TagListController() {
	}
	
	public void setChosenTags(TagList tags) {
		this.chosenTags = tags;
	}
	
	public TagList getChosenTags() {
		return this.chosenTags;
	}
	
	public void addChosenTag(ClaimTag tag) {
		this.chosenTags.add(tag);
	}
	
	public static TagListController getInstance(){
		if (instance == null){
			instance = new TagListController();
		}
		return instance;
	}
	
	public void initialize(Context context){
		this.context = context;
		loadTags();
	}
	
	
	public TagList getTagList() {
		return this.usersTags;
	}
	
	public void addTag(ClaimTag tag){
		usersTags.add(tag);
	}
	
	public void removeTag(ClaimTag tag){
		usersTags.remove(tag);
	}
	
	public void setTagName(ClaimTag tag, String name){
		tag.setName(name);
	}
	
	private void loadTags() {
		usersTags = LocalFileHelper.getInstance(this.context).getTags();
	}
}