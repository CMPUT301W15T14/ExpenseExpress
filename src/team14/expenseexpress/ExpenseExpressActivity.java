package team14.expenseexpress;

import team14.expenseexpress.model.User;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public abstract class ExpenseExpressActivity extends Activity {

	protected App app;
	protected User user;
	protected int mode;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		app = (App) getApplication();
		user = app.getUser();
		mode = app.getMode();
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
	
	
}