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
import team14.expenseexpress.model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

/**
 * Singleton with this public interface:
 * - Overwrite local claims list with any claims list
 * - Load all local claims
 * - Load only local claims made by the user (for claiming)
 * - Load only local claims not made by the user (for approving) 
 * 
 * @author Team14
 * @date March 10, 2015
 * @version 0.2
 */
public class FileHelper {
	private Context context;
	
	// Singleton
	private static FileHelper instance;
	
	private FileHelper(Context context){
		this.context = context;
	}
	
	public static FileHelper getInstance(Context context){
		if (instance == null){
			instance = new FileHelper(context);
		}
		return instance;
	}

	
	private static final String CLAIMS_FILENAME = "claims.ee";

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

	public void save(ArrayList<Claim> claims){
		save(claims, CLAIMS_FILENAME);
	}

	public ArrayList<Claim> getAllLocalClaims(){
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
}