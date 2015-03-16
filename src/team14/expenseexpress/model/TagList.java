package team14.expenseexpress.model;

import java.util.ArrayList;

public class TagList {
	private ArrayList<ClaimTag> tagList;
	
	public TagList() {
		tagList = new ArrayList<ClaimTag>();
	}

	public ArrayList<ClaimTag> getTags() {
		return this.tagList;
	}
	
	public void add(ClaimTag tag) {
		this.tagList.add(tag);
	}

	public void remove(ClaimTag tag) {
		this.tagList.remove(tag);
	}

	public int size() {
		return this.tagList.size();
	}

	public ClaimTag get(int position) {
		return this.tagList.get(position);
	}
}