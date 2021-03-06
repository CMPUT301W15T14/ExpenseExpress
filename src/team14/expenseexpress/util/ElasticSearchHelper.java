package team14.expenseexpress.util;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;

/**
 * @author  zbudinsk
 *
 * In Lab Code:  https://github.com/joshua2ua/AndroidElasticSearch  Accessed  April. 6, 2015
 * 
 * Allows the use of ElasticSearch for submitting/returning Claims.
 * 
 * @author Team 14
 *
 */


public class ElasticSearchHelper {
	private static final String SERVER_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/claim/";
	private static final String TAG = "ElasticSearchHelper";
	private Gson gson = new Gson();
    
	private static Context context;
	
    /**
	 */
    private static ElasticSearchHelper instance;
    
    
    private ElasticSearchHelper(Context context){
    	this.context = context;
    }
    
    /**
	 * @return
	 */
    public static ElasticSearchHelper getInstance(){
    	if (instance == null){
    		instance = new ElasticSearchHelper(context);
    	}
    	return instance;
    }
    
    public static ElasticSearchHelper getInstance(Context context){
    	if (instance == null){
    		instance = new ElasticSearchHelper(context);
    	}
    	return instance;
    }
	/**
	 * Attempts to add given claim to Server
	 * @param claim being added
	 * @return boolean, True if is successful
	 */
	private void add(Claim claim) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPut addRequest = new HttpPut(SERVER_URL + claim.getId());

			StringEntity stringEntity = new StringEntity(gson.toJson(claim));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Attempts to Delete given claim from Server
	 * @param claim being deleted
	 * @return boolean, True if is successful
	 */
	private void delete(Claim claim) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(SERVER_URL + claim.getId());
			deleteRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		};
	}
	/**
	 * Adds claim to AddClaimSync to be synchronized with rest of Server 
	 * @param claim to be added
	 */
	public void addClaim(Claim claim) {
		AddClaimSync task = new AddClaimSync(claim);
		task.execute();
	}
	/**
	 * Adds claim to DeleteClaimSync to be synchronized with rest of Server 
	 * @param claim to be deleted
	 */
	public void deleteClaim(Claim claim) {
		DeleteClaimSync task = new DeleteClaimSync(claim);
		task.execute();
	}
	/**
	 * Synchronizes the added claims with the Server
	 */
	private class AddClaimSync extends AsyncTask<Void, Void, Void> {
		/**
		 */
		private Claim claim;
		
		private AddClaimSync(Claim claim) {
			this.claim = claim;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			add(claim);
			return null;
		}
	}
	/**
	 * Synchronizes the deleted claims from the server
	 *
	 */
	private class DeleteClaimSync extends AsyncTask<Void, Void, Void> {

		private Claim claim;
		
		private DeleteClaimSync(Claim claim) {
			this.claim = claim;
		}
		@Override
		protected Void doInBackground(Void... params) {
			delete(claim);
			return null;
		}
	}

	/**
	 * Gets previously submitted claims from server and adds them to current list of claims
	 * 
	 * @return updated list of Claims
	 */
	public ArrayList<Claim> getSubmitted() {

		ArrayList<Claim> claims = new ArrayList<Claim>();

		
		HttpPost searchRequest = new HttpPost(SERVER_URL + "_search");

		SimpleSearchCommand command = new SimpleSearchCommand("*");

		String query = gson.toJson(command);
		Log.i(TAG, "Json command: " + query);

		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(query);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringEntity);
		
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = httpClient.execute(searchRequest);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		 // Parses the response of a search
		 
		Type searchResponseType = new TypeToken<SearchResponse<Claim>>() {}.getType();
		
		try {
			SearchResponse<Claim> esResponse = gson.fromJson(
					new InputStreamReader(response.getEntity().getContent()),
					searchResponseType);
			Hits<Claim> hits = esResponse.getHits();
			for(int i = 0;i < hits.getHits().size(); i++) {
				Claim claim = hits.getHits().get(i).getSource();
				claims.add(claim);
			}
			
		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			throw new RuntimeException(e);
		}
		
		return claims;
	}
	
	/**
	 * Gets previously returned claims from server and adds them to current list of claims
	 * 
	 * @return updated list of Claims
	 */
	public ArrayList<Claim> getReturned() {
		return getSubmitted();
	}
	
	//http://pulse7.net/android/check-internet-connection-android/     Accessed April.6th, 2015
	/**
	 * checks to see if can connect to Server
	 * @return True if can connect, false if cannot
	 */
	public static boolean isNetworkAvailable() {
		// Get Connectivity Manager class object from Systems Service
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
		
		// Get Network Info from connectivity Manager
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		
		// if no network is available networkInfo will be null
	    // otherwise check if we are connected
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}
	
	
}