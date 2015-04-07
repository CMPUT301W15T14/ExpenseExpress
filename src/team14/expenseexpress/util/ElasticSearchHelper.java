package team14.expenseexpress.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Base64;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.ReceiptController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Receipt;

public class ElasticSearchHelper {
	private static final String SUBMITTED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/submitted/";
	private static final String RESUBMITTED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/resubmitted/";
	private static final String RETURNED_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/returned/";
	private static final String RECEIPT_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/receipts/";
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
	/**
	 * Attempts to add given claim to Server
	 * @param claim being added
	 * @return boolean, True if is successful
	 */
	private boolean add(Claim claim) {
		boolean value = false;
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
			value = true;

		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return value;
	}
	/**
	 * Attempts to Delete given claim from Server
	 * @param claim being deleted
	 * @return boolean, True if is successful
	 */
	private boolean delete(Claim claim) {
		boolean value = false;
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
			value = true;

		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return value;
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
		DeleteClaimSync task = new DeleteClaimSync();
		task.execute(claim);
	}
	/**
	 * 
	 *Synchronizes the added claims with the Server
	 */
	private class AddClaimSync extends AsyncTask<Void, Void, Boolean> {
		
		Claim claim;
		
		private AddClaimSync(Claim claim) {
			this.claim = claim;
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			return add(claim);
		}
		
		protected void onPostExecute(Boolean result) {
			if((result == false) && (Mode.get() == Mode.CLAIMANT)) {
				claim.setStatus(team14.expenseexpress.model.Status.IN_PROGRESS);
				Toast.makeText(context, "Failed to Submit", Toast.LENGTH_SHORT).show();
			}
			if((result == false) && (Mode.get() == Mode.APPROVER)) {
				claim.setStatus(team14.expenseexpress.model.Status.SUBMITTED);
				Toast.makeText(context, "Failed to Return", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	/**
	 * Synchronizes the deleted claims from the server
	 *
	 */
	private class DeleteClaimSync extends AsyncTask<Claim, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Claim... params) {
			return delete(params[0]);
		}
		
		protected void onPostExecute(Boolean result) {
			
		}
		
	}

	/**
	 * Gets previously submitted claims from server and adds them to current list of claims
	 * 
	 * @return updated list of Claims
	 */
	public ArrayList<Claim> getSubmitted() {


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
	
	
	
	
	
	private void getReceipt(String uri) {
		SearchHit<Byte[]> sr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(RECEIPT_URL + uri);
		
		HttpResponse response = null;

		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		
		Type searchHitType = new TypeToken<SearchHit<Byte[]>>() {}.getType();

		try {
			sr = gson.fromJson(
					new InputStreamReader(response.getEntity().getContent()),
					searchHitType);
		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ByteArrayOutputStream blob = new ByteArrayOutputStream();
		byte[] bitmapdata = blob.toByteArray();
		Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata , 0, bitmapdata .length);

	}
	
	public void getReceiptFromElastic(Receipt receipt){
		getReceiptSync task = new getReceiptSync(receipt);
		task.execute();
	}
	
	private class getReceiptSync extends AsyncTask<Void, Void, Boolean> {
		Receipt receipt;
		
		private getReceiptSync(Receipt receipt) {
			this.receipt = receipt;
		}
		@Override
		protected Boolean doInBackground(Void ...params) {
			//getReceipt(receipt);
			return true;
		}

		
	}
	private void addReceipt(Receipt receipt) {
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost addRequest = new HttpPost(RECEIPT_URL + receipt.getUri().toString());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap photo = ReceiptController.getInstance().getBitmap(receipt, context);
			photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] imageBytes = baos.toByteArray();
			String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
			StringEntity stringEntity = new StringEntity(gson.toJson(encodedImage));
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
	
	public void addReceiptToElastic(Receipt receipt){
		AddReceiptSync task = new AddReceiptSync(receipt);
		task.execute();
	}
	
	private class AddReceiptSync extends AsyncTask<Void, Void, Boolean> {
		Receipt receipt;
		
		private AddReceiptSync(Receipt receipt) {
			this.receipt = receipt;
		}
		@Override
		protected Boolean doInBackground(Void ...params) {
			addReceipt(receipt);
			return true;
		}

		
	}
	//http://stackoverflow.com/questions/22443853/check-http-connection-to-a-url-android    Accessed April.6th, 2015
	/*
	private boolean isConnectedToServer() {
		try{
            URL myUrl = new URL("http://cmput301.softwareprocess.es:8080/cmput301w15t14/");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(500);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
	}
	*/
	
}