package team14.expenseexpress.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.User;

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
	
	private static final String CLAIMS_FILENAME = "claims.ee";
	private static final String TAGS_FILENAME = "tags.ee";
	
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
	
	/*
	public static FileHelper getHelper() {
		if(fileHelper == null) {
			throw new RuntimeException("Missing instance of FileHelper");
		}
		return fileHelper;
	}
	*/
	
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

	
	public void saveClaims(ArrayList<Claim> claims){
		save(claims, CLAIMS_FILENAME);
	}

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

	public ArrayList<ClaimTag> getTags() {
		Gson gson = new Gson();
		ArrayList<ClaimTag> tags = new ArrayList<ClaimTag>();
		try {
			FileInputStream fis = context.openFileInput(TAGS_FILENAME);
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
			tags = new ArrayList<ClaimTag>();
		}
		return tags; 
	}

	public void saveTags(ArrayList<ClaimTag> tags) {
		save(tags, TAGS_FILENAME);
	}

}