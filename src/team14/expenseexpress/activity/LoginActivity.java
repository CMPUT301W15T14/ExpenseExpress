package team14.expenseexpress.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import team14.expenseexpress.App;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.model.User;


public class LoginActivity extends ExpenseExpressActivity {

    private EditText editText_name;
    private AlertDialog modeDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_name = (EditText) findViewById(R.id.loginEditText);
        buildModeDialog();

    }

    private void buildModeDialog() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Log in as approver or claimant?")
        .setNegativeButton("Approver", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				app.setMode(App.APPROVER_MODE);
			    startClaimsListActivity();
			}
		})
		.setPositiveButton("Claimant", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				app.setMode(App.CLAIMANT_MODE);
				startClaimsListActivity();
			}
		});
        
        modeDialog = builder.create();                		
	}


	public void onClick_signIn(View view){
    	User user = new User(editText_name.getText().toString());
    	app.setUser(user);
    	modeDialog.show();
    }


	private void startClaimsListActivity() {
        Intent intent = new Intent(this, ClaimListActivity.class);
        startActivity(intent);
    }
}