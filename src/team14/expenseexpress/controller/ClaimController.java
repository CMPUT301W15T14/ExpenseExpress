package team14.expenseexpress.controller;

import android.content.Context;

public class ClaimController {
	
	//Singleton
	private Context context;	
	
	private static ClaimController instance;
	
	private ClaimController(Context context){
		this.context = context;
	}
	
	public ClaimController getInstance(Context context){
		if (instance == null){
			instance = new ClaimController(context);
		}
		return instance;
	}
	
	
}