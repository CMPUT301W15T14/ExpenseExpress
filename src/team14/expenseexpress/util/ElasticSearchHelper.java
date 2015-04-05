package team14.expenseexpress.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.ClaimList;

public class ElasticSearchHelper {
	private static final String CLAIMANT_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/users/claimant/";
	private static final String APPROVER_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/users/approver/";
	private static final String SUBMITTED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/users/submitted/";
	private static final String RETURNED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/users/returned/";
	private static final String TAG = "ElasticSearchHelper";
	private Gson gson = new Gson();
	
    private Context context;
    
    private static ElasticSearchHelper instance;
    
    private ElasticSearchHelper(Context context){
    	this.context = context;
    }
    
    public static ElasticSearchHelper getInstance(Context context){
    	if (instance == null){
    		instance = new ElasticSearchHelper(context);
    	}
    	return instance;
    }
    
    /**
	 * Adds a new movie
	 */
	public void saveRemoteClaimList(ClaimList claims) {
		HttpClient httpClient = new DefaultHttpClient();
		
		String serverUrl = new String();
		if(Mode.get() == Mode.CLAIMANT) {
			serverUrl = CLAIMANT_URL + UserController.getInstance().getCurrentUser().getName();
		} else if (Mode.get() == Mode.APPROVER) {
			serverUrl = APPROVER_URL + UserController.getInstance().getCurrentUser().getName();
		}

		try {
			HttpPut addRequest = new HttpPut(serverUrl);

			StringEntity stringEntity = new StringEntity(gson.toJson(claims));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    /**
	 * Get a movie with the specified id
	 */
	public ClaimList getRemoteClaimList() {
		ClaimList claims = null;
		HttpClient httpClient = new DefaultHttpClient();

		String serverUrl = new String();
		if(Mode.get() == Mode.CLAIMANT) {
			serverUrl = CLAIMANT_URL + UserController.getInstance().getCurrentUser().getName();
		} else if (Mode.get() == Mode.APPROVER) {
			serverUrl = APPROVER_URL + UserController.getInstance().getCurrentUser().getName();
		}
		
		HttpGet httpGet = new HttpGet(serverUrl);
		
		HttpResponse response = null;

		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}

		try {
			claims = gson.fromJson(
					new InputStreamReader(response.getEntity().getContent()),
					ClaimList.class);
		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return claims;

	}

}