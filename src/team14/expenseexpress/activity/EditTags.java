package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.R;
import team14.expenseexpress.activity.TagListDialogFragment.TagsListAdapter;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.TagListController;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import team14.expenseexpress.model.TagList;
import android.os.Bundle;
import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EditTags extends Activity {
	private EditTags activity;

	private ListView tagListView;
	private ArrayAdapter<ClaimTag> tagAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tags);
		
		final ListView taglistview = (ListView) findViewById(R.id.editTagList);

		TagListAdapter adapter = new TagListAdapter(this);
		taglistview.setAdapter(adapter);
	}
	
	
	public void addNewTags(View v) {
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	public void acceptTags(View v) {
		finish();
	}
}
