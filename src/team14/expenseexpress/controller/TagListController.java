package team14.expenseexpress.controller;

import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.TagList;
import team14.expenseexpress.util.LocalFileHelper;

/**
 * Singleton controller for tags.
 */
public class TagListController {
	
	/**
	 */
	private TagList usersTags;
	/**
	 */
	private TagList chosenTags;
	
	// singleton
	/**
	 */
	private static TagListController instance = null;
	private TagListController() {
	}
	/**
	 * sets tags to chosen tags
	 * @param  tags
	 */
	public void setChosenTags(TagList tags) {
		this.chosenTags = tags;
	}
	/**
	 * returns the chosen tags
	 * @return  instance of chosen tags
	 */
	public TagList getChosenTags() {
		return this.chosenTags;
	}
	/**
	 * 
	 * @param tag the tag to be added to chosen tags
	 */
	public void addChosenTag(ClaimTag tag) {
		this.chosenTags.add(tag);
	}
	/**
	 * If no chosen instance of TagListController, returns empty instance
	 * @return  instance of TagListController
	 */
	public static TagListController getInstance(){
		if (instance == null){
			instance = new TagListController();
		}
		return instance;
	}
	/**
	 * initializes TagList
	 */
	public void initialize(){
		this.chosenTags = new TagList();
		loadTags();
	}
	
	/**
	 * 
	 * @return instance of current usersTags
	 */
	public TagList getTagList() {
		return this.usersTags;
	}
	/**
	 * Adds tag to usersTags
	 * @param tag ClaimTag
	 */
	public void addTag(ClaimTag tag){
		usersTags.add(tag);
		LocalFileHelper.getInstance().saveTags(this.usersTags);
	}
	/**
	 * removes tag from usersTags
	 * @param tag ClaimTag
	 */
	public void removeTag(ClaimTag tag){
		usersTags.remove(tag);
		LocalFileHelper.getInstance().saveTags(this.usersTags);
	}
	/**
	 * names the instance of the given tag the given name.
	 * @param tag instance of tag
	 * @param name the name of the given tag
	 */
	public void setTagName(ClaimTag tag, String name){
		tag.setName(name);
		LocalFileHelper.getInstance().saveTags(this.usersTags);
	}
	/**
	 * updates usersTags
	 */
	private void loadTags() {
		usersTags = LocalFileHelper.getInstance().getTags();
	}
}