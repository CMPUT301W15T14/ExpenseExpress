package team14.expenseexpress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressApplication;
import team14.expenseexpress.R;
import team14.expenseexpress.activity.claimant.ClaimListActivity;
import team14.expenseexpress.controller.UsernameListController;
import team14.expenseexpress.model.User;
import team14.expenseexpress.model.Username;

/**
 * 0) Login Screen This will be the launcher activity, a user must sign in with their name to begin.
 */
public class LoginActivity extends ActionBarActivity {

    private ArrayList<Username> usernames;
    private EditText editText_name;
    private ExpenseExpressApplication application;
    private UsernameListController usernameListController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        application = (ExpenseExpressApplication) getApplication();
        usernameListController = new UsernameListController(application);
        loadUserIDs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadUserIDs() {
        usernames = application.getUsernames();
    }

    private void onClick_signIn(View view){
        editText_name = (EditText)findViewById(R.id.name);
        String name = editText_name.getText().toString();
        long id = usernameListController.getId(name);
        if (id == Username.NOT_IN_LIST) {
            id = createUser(name);
        } else setClaims(id); // This important step sets up the list of Claims for the rest of the Application to use!
        startClaimsListActivity(id);
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