package team14.expenseexpress;

import team14.expenseexpress.model.User;
import team14.expenseexpress.util.ElasticSearchHelper;
import team14.expenseexpress.util.FileHelper;
import android.app.Activity;
import android.os.Bundle;

public abstract class ExpenseExpressActivity extends Activity {

	protected App app;
	protected User user;
	protected int mode;
	protected FileHelper fileHelper;
	protected ElasticSearchHelper elasticSearchHelper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		app = (App) getApplication();
		user = app.getUser();
		mode = app.getMode();
		fileHelper = app.getFileHelper();
		elasticSearchHelper = app.getElasticSearchHelper();
	}
}
