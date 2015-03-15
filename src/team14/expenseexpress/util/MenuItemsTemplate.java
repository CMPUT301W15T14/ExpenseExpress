/*package team14.expenseexpress.util;

//------------TEMPLATE!!!-----------------\\
 //MUST ADD TO MENU XML FOR EACH ACTIVITY\\
import team14.expenseexpress.model.User;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MenuItemsTemplate extends Activity{
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_insert_my, menu);//CHANGE for each activity
		return true;
	}
	
	public void toLogin(MenuItem menu){
		User.setInstance(null); //how access user?
		Intent intent = new Intent(InsertMyActivity.this, LoginActivity.class);//CHANGE for each activity
		startActivity(intent);
	}
	public void toApproverClaimList(MenuItem menu){
		Intent intent = new Intent(InsertMyActivity.this, ApproverClaimListActivity.class);//CHANGE for each activity
		startActivity(intent);
	}
}
*/