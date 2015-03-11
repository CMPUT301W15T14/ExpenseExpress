package team14.expenseexpress.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import team14.expenseexpress.App;
import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.model.User;

/*
 * Done
 */
public class LoginActivity extends ExpenseExpressActivity {

    private EditText editText_name;
    private AlertDialog modeDialog;
    private AlertDialog confirmNameDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_name = (EditText) findViewById(R.id.loginEditText);
        buildModeDialog();
        buildConfirmNameDialog();
    }

    private void buildConfirmNameDialog() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Confirm your name.\n\nIt's case-sensitive.")
    	.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				modeDialog.show();
				dialog.dismiss();
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
    	
    	confirmNameDialog = builder.create();		
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
		}).setTitle(editText_name.getText().toString());
        
        modeDialog = builder.create();                		
	}

	// The listener is defined in the XML (onClick attribute)
	public void onClick_signIn(View view){
		String name = editText_name.getText().toString();
		if (name.length()==0){
			toast("Can't be empty");
			return;
		}
    	User user = new User(name);
    	app.setUser(user);
    	confirmNameDialog.setTitle(editText_name.getText().toString());
    	confirmNameDialog.show();
    }


	private void startClaimsListActivity() {
        startActivity(new Intent(this, ClaimListActivity.class));
    }
}
