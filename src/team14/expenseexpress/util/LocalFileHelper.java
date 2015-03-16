package team14.expenseexpress.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.TagList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

/**
 * Singleton with these public methods:
 * - Overwrite local claims list with any claims list
 * - Load only local claims made by the user (for claiming)
 * - Load only local claims not made by the user (for approving) 
 * 
 */
public class LocalFileHelper {
	
	private static final String CLAIMANT_FILENAME = "ee.claimant_";
	private static final String APPROVER_FILENAME = "ee.approver"; 
	private static final String TAGS_FILENAME = "ee.tags_";
	//private static final String OFFLINE_FILENAME = "ee.offline"; TODO: implement in pp5
	
	private Context context;
	
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
	}

	
	public void saveClaims(ClaimList claims){
		switch(Mode.get()) {
		case Mode.CLAIMANT:
			save(claims,CLAIMANT_FILENAME + UserController.getInstance().getCurrentUser().getName());
			break;
		case Mode.APPROVER:
			save(claims, APPROVER_FILENAME);
			break;
		}
	}

	public ClaimList loadClaims() {
		Gson gson = new Gson();
		ClaimList claims = new ClaimList();
		
		if(Mode.get() == Mode.CLAIMANT) {
			try {
				FileInputStream fis = context.openFileInput(CLAIMANT_FILENAME + UserController.getInstance().getCurrentUser().getName());
				InputStreamReader isr = new InputStreamReader(fis);
				Type dataType = new TypeToken<ArrayList<Claim>>() {	}.getType();
				claims = gson.fromJson(isr, dataType);
				fis.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if(Mode.get() == Mode.APPROVER) {
			try {
				FileInputStream fis = context.openFileInput(APPROVER_FILENAME);
				InputStreamReader isr = new InputStreamReader(fis);
				Type dataType = new TypeToken<ArrayList<Claim>>() {	}.getType();
				claims = gson.fromJson(isr, dataType);
				fis.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (claims == null) {
			claims = new ClaimList();
		}
		return claims;
	}
	
	
	/*
	private ArrayList<Claim> getAllLocalClaims(){
		Gson gson = new Gson();
		ArrayList<Claim> claims = new ArrayList<Claim>();
		try {
			FileInputStream fis = context.openFileInput(CLAIMS_FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);
			Type dataType = new TypeToken<ArrayList<Claim>>() {	}.getType();
			claims = gson.fromJson(isr, dataType);
			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (claims == null) {
			claims = new ArrayList<Claim>();
		}
		return claims; 
	}
	
	public ArrayList<Claim> getLocalClaimsForClaimant(User claimant){
		ArrayList<Claim> allClaims = getAllLocalClaims();
		ArrayList<Claim> claimsByClaimant = new ArrayList<Claim>();
		for (int i = 0; i<allClaims.size(); i++){
			if (allClaims.get(i).getClaimant().equals(claimant)){ //overridden equals method
				claimsByClaimant.add(allClaims.get(i));
			}
		}
		return claimsByClaimant;
	}
	
	public ArrayList<Claim> getLocalClaimsForApprover(User approver){
		ArrayList<Claim> allClaims = getAllLocalClaims();
		ArrayList<Claim> claimsForApprover = new ArrayList<Claim>();
		for (int i = 0; i<allClaims.size(); i++){
			// Opposite of getLocalClaimsForClaimant
			if (!allClaims.get(i).getClaimant().equals(approver)){
				claimsForApprover.add(allClaims.get(i));
			}
		}
		return claimsForApprover;
	}

*/

	public TagList getTags() {
		Gson gson = new Gson();
		TagList tags = new TagList();
		try {
			FileInputStream fis = context.openFileInput(TAGS_FILENAME + UserController.getInstance().getCurrentUser().getName());
			InputStreamReader isr = new InputStreamReader(fis);
			Type dataType = new TypeToken<ArrayList<ClaimTag>>() {	}.getType();
			tags = gson.fromJson(isr, dataType);
			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (tags == null) {
			tags = new TagList();
		}
		return tags; 
	}

	public void saveTags(ArrayList<ClaimTag> tags) {
		save(tags, TAGS_FILENAME + UserController.getInstance().getCurrentUser().getName());
	}

}