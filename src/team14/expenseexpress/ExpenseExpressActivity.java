package team14.expenseexpress;

import team14.expenseexpress.model.User;
import android.app.Activity;
import android.os.Bundle;

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
}