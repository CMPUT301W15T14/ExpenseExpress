package team14.expenseexpress.model;

import java.util.ArrayList;

import team14.expenseexpress.util.LocalFileHelper;

import android.content.Context;

public class TagList {
	private ArrayList<ClaimTag> tags;
	private Context context;
	
	private static TagList instance;
	
	private TagList(Context context){
		this.context = context;
		loadTags();
	}
	
	private void loadTags() {
		LocalFileHelper helper = LocalFileHelper.getInstance(context);
		tags = helper.getTags();		
	}

	public static TagList getInstance(Context context){
		if (instance == null){
			instance = new TagList(context);
		}
		return instance;
	}
	
	public ArrayList<ClaimTag> get(){
		return tags;
	}
	
	public void add(ClaimTag tag){
		tags.add(tag);
	}
	
	public void remove(ClaimTag tag){
		tags.remove(tag);
	}
	
	public void save(){
		LocalFileHelper helper = LocalFileHelper.getInstance(context);
		helper.saveTags(tags);
	}
}