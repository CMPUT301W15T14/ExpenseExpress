package team14.expenseexpress.util;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

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


import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.model.Claim;

public class ElasticSearchHelper {
	private static final String SUBMITTED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/submitted/";
	private static final String RETURNED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/returned/";
	private static final String RECEIPT_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/receipts";
	private static final String TAG = "ElasticSearchHelper";
	private Gson gson = new Gson();
    
	private static Context context;
	
    private static ElasticSearchHelper instance;
    
    
    private ElasticSearchHelper(Context context){
    	this.context = context;
    }
    
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
    
	private void add(Claim claim) {
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
	
	private void delete(Claim claim) {
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
	
	public void addClaim(Claim claim) {
		if(isNetworkAvailable() && isConnectedToServer()) {
			AddClaimSync task = new AddClaimSync();
			task.execute(claim);
		} else {
			
		}
	}

	public void deleteClaim(Claim claim) {
		if(isNetworkAvailable() && isConnectedToServer()) {
			DeleteClaimSync task = new DeleteClaimSync();
			task.execute(claim);
		} else {
			
		}
	}
	
	public class AddClaimSync extends AsyncTask<Claim, Void, String> {

		@Override
		protected String doInBackground(Claim... params) {
			add(params[0]);
			return null;
		}
		
	}
	
	public class DeleteClaimSync extends AsyncTask<Claim, Void, String> {

		@Override
		protected String doInBackground(Claim... params) {
			delete(params[0]);
			return null;
		}
		
	}
	
	public ArrayList<Claim> getClaims() {
		ArrayList<Claim> claims = new ArrayList<Claim>();

		HttpPost searchRequest = new HttpPost(SUBMITTED_URL + "_search");

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
		
		/**
		 * Parses the response of a search
		 */
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
	
	
	//http://pulse7.net/android/check-internet-connection-android/     Accessed April.6th, 2015
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
	
	//http://stackoverflow.com/questions/22443853/check-http-connection-to-a-url-android    Accessed April.6th, 2015
	private boolean isConnectedToServer() {
		try{
            URL myUrl = new URL("http://cmput301.softwareprocess.es:8080/cmput301w15t14");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(500);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
	} 
	
}