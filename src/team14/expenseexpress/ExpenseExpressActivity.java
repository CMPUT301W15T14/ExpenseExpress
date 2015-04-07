package team14.expenseexpress;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * The Main Activity that runs on Application start.
 * 
 * @author Team 14
 * 
 */

public abstract class ExpenseExpressActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	protected void toast(CharSequence message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
	protected void hide(View view){
		view.setVisibility(View.GONE);
	}
	
	protected void show(View view){
		view.setVisibility(View.VISIBLE);
	}
	
	protected void log(String message){
		Log.d("Exp^2", message);
	}
	
	
}