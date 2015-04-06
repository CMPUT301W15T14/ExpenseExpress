package team14.expenseexpress.controller;


import team14.expenseexpress.model.User;


public class UserController {
	//private final Gson gson = new Gson();
	private User currentUser = null;
	//private static final String USER_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/user/";
	//private static final String TAG = "UserController";
	
	//private static Context context;
	
	private static UserController instance;
	
	private UserController(){
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
	
	
	
	/*
	private User getUser(String name) {
		SearchHit<User> sr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(USER_URL + name);

		HttpResponse response = null;

		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		
		Type searchHitType = new TypeToken<SearchHit<User>>() {}.getType();

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

		return sr.getSource();
	}
	
	public boolean addUser(User user) {
		boolean value = false;
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost addRequest = new HttpPost(USER_URL + user.getName());
	
			StringEntity stringEntity = new StringEntity(gson.toJson(user));
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
	
	
	private class GetUserSync extends AsyncTask<String, Void, User> {
		
		@Override
		protected User doInBackground(String ...params) {
			return getUser(params[0]);
		}
		
		protected void onPostExecute(User result) {
			if(result == null) {
				UserController.getInstance().setCurrentUser(null);
				Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
			} else {
				UserController.getInstance().setCurrentUser(result);
				Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	private class AddUserSync extends AsyncTask<Void, Void, Boolean> {
		User user;
		
		private AddUserSync(User user) {
			this.user = user;
		}
		@Override
		protected Boolean doInBackground(Void ...params) {
			return addUser(user);
		}
		
		protected void onPostExecute(Boolean result) {
			if(result == true) {
				UserController.getInstance().setCurrentUser(null);
				Toast.makeText(context, "Create Account Fail", Toast.LENGTH_SHORT).show();
			} else {
				UserController.getInstance().setCurrentUser(user);
				Toast.makeText(context, "Create Account Success", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	public void logIn(String name) {
		GetUserSync task = new GetUserSync();
		task.execute(name);
	}
	
	public void createAccount(User user) {
		AddUserSync task = new AddUserSync(user);
		task.execute();
	}
	
	public void removeUser(User user) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(user.getResourceUrl() + user.getName());
			deleteRequest.setHeader("Assecpt","application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
