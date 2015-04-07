package team14.expenseexpress.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.TagList;
import android.content.Context;
import android.os.AsyncTask;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Singleton with these public methods:
 * - Overwrite local claims list with any claims list
 * - Load only local claims made by the user (for claiming)
 * - Load only local claims not made by the user (for approving) 
 * 
 */
public class LocalFileHelper {
	
	private static final String CLAIMANT_FILENAME = "ee.claimant_";
	private static final String APPROVER_FILENAME = "ee.approver_"; 
	private static final String TAGS_FILENAME = "ee.tags_";
	
	private static Context context;
	
	// Singleton
	private static LocalFileHelper fileHelper;
	
	private LocalFileHelper(Context context){
		this.context = context;
	}
	
	public static LocalFileHelper getInstance(Context context){
		if (fileHelper == null){
			fileHelper = new LocalFileHelper(context);
		}
		return fileHelper;
	}
	
	public static LocalFileHelper getInstance(){
		if (fileHelper == null){
			fileHelper = new LocalFileHelper(context);
		}
		return fileHelper;
	}
	
	private void save(Object data, String filename) {
		Gson gson = new Gson();
		try {
			FileOutputStream fos = context.openFileOutput(filename,
					Context.MODE_PRIVATE);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(data, osw);
			osw.flush();
			fos.close();
			osw.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}

	
	public void saveClaims(ClaimList claims){
		switch(Mode.get()) {
		case Mode.CLAIMANT:
			save(claims,CLAIMANT_FILENAME + UserController.getInstance().getCurrentUser().getName());
			break;
		case Mode.APPROVER:
			save(claims, APPROVER_FILENAME + UserController.getInstance().getCurrentUser().getName());
			break;
		case Mode.OFFLINE:
			save(claims,CLAIMANT_FILENAME + UserController.getInstance().getCurrentUser().getName());
		}
	}

	public ClaimList loadClaims() {
		Gson gson = new Gson();
		ClaimList claims = new ClaimList();
		
		String FileUrl = new String("");
		if((Mode.get() == Mode.CLAIMANT)||(Mode.get() == Mode.OFFLINE)) {
			FileUrl = CLAIMANT_FILENAME + UserController.getInstance().getCurrentUser().getName();
		} else if(Mode.get() == Mode.APPROVER) {
			FileUrl = APPROVER_FILENAME + UserController.getInstance().getCurrentUser().getName();
		}
		
		try {
			FileInputStream fis = context.openFileInput(FileUrl);
			InputStreamReader isr = new InputStreamReader(fis);
			Type dataType = new TypeToken<ClaimList>() {	}.getType();
			claims = gson.fromJson(isr, dataType);
			fis.close();
			isr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (claims == null) {
			claims = new ClaimList();
		}
		
		return claims;
	}
	

	public TagList getTags() {
		Gson gson = new Gson();
		TagList tags = new TagList();
		try {
			FileInputStream fis = context.openFileInput(TAGS_FILENAME + UserController.getInstance().getCurrentUser().getName());
			InputStreamReader isr = new InputStreamReader(fis);
			Type dataType = new TypeToken<TagList>() {	}.getType();
			tags = gson.fromJson(isr, dataType);
			fis.close();
			isr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e){
			tags = new TagList();
		}
		if (tags == null) {
			tags = new TagList();
		}
		return tags; 
	}

	public void saveTags(TagList tags) {
		save(tags, TAGS_FILENAME + UserController.getInstance().getCurrentUser().getName());
	}

}