package team14.expenseexpress.model;

import java.util.ArrayList;

public class TagList {
	private ArrayList<ClaimTag> tagList;
    /**
     * Constructor that creates a new ArrayList <ClaimTag>
     *	
     * @return password
     */
	public TagList() {
		tagList = new ArrayList<ClaimTag>();
	}
    /**
     * getter that retrieves the ArrayList of ClaimTags.
     *	
     * @return ArrayList<ClaimTag>
     */
	public ArrayList<ClaimTag> getTags() {
		return this.tagList;
	}
    /**
     * Method to add a ClaimTag to tagList.
     *	
     * @return ClaimTag tag
     */
	public void add(ClaimTag tag) {
		this.tagList.add(tag);
	}
	/**
     * Method to remove a ClaimTag from tagList.
     *	
     * @return ClaimTag tag
     */
	public void remove(ClaimTag tag) {
		this.tagList.remove(tag);
	}
	/**
     * Method to get the size oftagList.
     *	
     * @return int size
     */
	public int size() {
		return this.tagList.size();
	}
	/**
     * Method to get a ClaimTag at a certain indextagList.
     * @param int position
     * @return ClaimTag
     */
	public ClaimTag get(int position) {
		return this.tagList.get(position);
	}
}