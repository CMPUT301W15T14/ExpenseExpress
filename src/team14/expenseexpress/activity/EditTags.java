package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.ClaimTag;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EditTags extends Activity {


	private ListView tagListView;
	private ArrayAdapter<ClaimTag> tagAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tags);
		
		tagListView = (ListView) findViewById(R.id.ListView);
		tagAdapter = new ArrayAdapter<ClaimTag>(this, R.layout.listtextview, ClaimController.getInstance().getSelectedClaim().getTags());
		tagListView.setAdapter(tagAdapter);
		tagListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
         });

	}
	
	public void addNewTags(View v) {
		
	}
	
	public void acceptTags(View v) {
		
		finish();
	}
}
