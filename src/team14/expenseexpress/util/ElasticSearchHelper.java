package team14.expenseexpress.util;


import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;


import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;

public class ElasticSearchHelper {
	private static final String SUBMITTED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/submitted";
	private static final String RETURNED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/returned/";
	private static final String RECEIPT_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/receipts";
	private static final String TAG = "ElasticSearchHelper";
	private Gson gson = new Gson();
    
    private static ElasticSearchHelper instance;
    
    private ElasticSearchHelper(){
    }
    
    public static ElasticSearchHelper getInstance(){
    	if (instance == null){
    		instance = new ElasticSearchHelper();
    	}
    	return instance;
    }
    
	public void addClaim(Claim claim) {
		HttpClient httpClient = new DefaultHttpClient();
		
		String serverUrl = new String();
		if(Mode.get() == Mode.CLAIMANT) {
			serverUrl = SUBMITTED_URL + String.valueOf(claim.getId());
		} else if (Mode.get() == Mode.APPROVER) {
			serverUrl = RETURNED_URL + claim.getClaimant().getName() + "/" + String.valueOf(claim.getId());
		}

		try {
			HttpPut addRequest = new HttpPut(serverUrl);

			StringEntity stringEntity = new StringEntity(gson.toJson(claim));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteClaim(Claim claim) {
		HttpClient httpClient = new DefaultHttpClient();

		String serverUrl = new String();
		if(Mode.get() == Mode.CLAIMANT) {
			serverUrl = RETURNED_URL + claim.getClaimant().getName() + "/" + String.valueOf(claim.getId());
		} else if (Mode.get() == Mode.APPROVER) {
			serverUrl = SUBMITTED_URL + String.valueOf(claim.getId());
		}
		
		try {
			HttpDelete deleteRequest = new HttpDelete(serverUrl);
			deleteRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}