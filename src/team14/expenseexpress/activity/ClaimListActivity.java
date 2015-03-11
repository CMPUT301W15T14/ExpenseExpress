package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.R.layout;
import team14.expenseexpress.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ClaimListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_list_ok);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_list, menu);
		return true;
	}

}
