package team14.expenseexpress.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

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
	private static final String OFFLINE_FILENAME = "ee.offline_";
	
	private static Context context;
	
	// Singleton
	private static LocalFileHelper fileHelper;
	private ElasticSearchHelper elasticHelper;
	
	private LocalFileHelper(Context context){
		this.context = context;
		this.elasticHelper = ElasticSearchHelper.getInstance(context);
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
		SaveSyncRunner runner = new SaveSyncRunner();
		switch(Mode.get()) {
		case Mode.CLAIMANT:
			save(claims,CLAIMANT_FILENAME + UserController.getInstance().getCurrentUser().getName());
			runner.execute(claims);
			break;
		case Mode.APPROVER:
			save(claims, APPROVER_FILENAME + UserController.getInstance().getCurrentUser().getName());
			runner.execute(claims);
			break;
		case Mode.OFFLINE:
			save(claims, OFFLINE_FILENAME +  UserController.getInstance().getCurrentUser().getName());
		}
	}

	public ClaimList loadClaims() {
		Gson gson = new Gson();
		ClaimList claims = new ClaimList();
		
		String FileUrl = new String("");
		if(Mode.get() == Mode.CLAIMANT) {
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
	
	private class SaveSyncRunner extends AsyncTask<ClaimList, Void, Void> {

		@Override
		protected Void doInBackground(ClaimList... params) {
			ClaimList claims = params[0];
			elasticHelper.saveRemoteClaimList(claims);
			try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.interrupted();
            } catch (Exception e) {
            	e.printStackTrace();
            }
			return null;
		}	
	}
	/*
	private class LoadSyncRunner extends AsyncTask<Params, Progress, Result> {

		@Override
		protected Result doInBackground(Params... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private void merge() {
		
	}
	*/
	
	public TagList getTags() {
		Gson gson = new Gson();
		TagList tags = new TagList();
		try {
			FileInputStream fis = context.openFileInput(TAGS_FILENAME + UserController.getInstance().getCurrentUser().getName());
			InputStreamReader isr = new InputStreamReader(fis);
			Type dataType = new TypeToken<TagList>() {	}.getType();
			tags = gson.fromJson(isr, dataType);
			fis.close();

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