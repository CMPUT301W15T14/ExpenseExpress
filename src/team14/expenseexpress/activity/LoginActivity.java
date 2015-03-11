package team14.expenseexpress.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressApplication;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.User;
import team14.expenseexpress.model.Username;
import team14.expenseexpress.util.GsonHelper;


public class LoginActivity extends Activity {

    private EditText editText_name;
    private ExpenseExpressApplication application;
    private GsonHelper gh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        application = (ExpenseExpressApplication) getApplication();
    }


    public void onClick_signIn(View view){
    	editText_name = (EditText)findViewById(R.id.signIn);
    	String name = editText_name.getText().toString();
    	synchronizeUser(name);
    	
    }

    private void synchronizeUser(String name) {
    	User localUser = gh.loadUser(User.LOCALID);
    	
		// TODO Auto-generated method stub
		
	}


	private void getUser(String name) {
    	    	
	}


	private User loadUser(String name) {
		

		return null;
	}


	private void startClaimsListActivity(long id) {
        Intent intent = new Intent(this, ClaimListActivity.class);
        intent.putExtra(application.KEY, id);
        startActivity(intent);
    }

    private long createUser(String name) {
        User user = new User(name);
        return user.getUsername().getId();
    }

    private void setClaims(long id) {
        application.synchronizeClaims(id);
    }
}