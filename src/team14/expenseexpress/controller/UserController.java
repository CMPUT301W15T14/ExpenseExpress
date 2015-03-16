package team14.expenseexpress.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import com.google.gson.Gson;
import team14.expenseexpress.model.User;

public class UserController {
	private Gson gson = new Gson();
	private User currentUser;
	private static final String TAG = "UserController";
	
	private static UserController instance = null;;
	private UserController() {
	}

	public static UserController getInstance() {
		if(instance == null) {
			instance = new UserController();
		}
		return instance;
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	public void getUser(User user) {
		//TODO: Server controls for Logging In...
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet getRequest = new HttpGet(user.getResourceUrl() + user.getName());
			getRequest.setHeader("Accept", "user.json");
			
			HttpResponse response = httpClient.execute(getRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Always attempt to getUser first, then add if not returned...
		
	}
	
	private void addUser(User user) {
		//TODO: Server controls for Logging In...
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost addRequest = new HttpPost(user.getResourceUrl() + user.getName());

			StringEntity stringEntity = new StringEntity(gson.toJson(user));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "user.json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeUser(User user) {
		//TODO: Server controls for Logging In...
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(user.getResourceUrl() + user.getName());
			deleteRequest.setHeader("Assecpt","user.json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
