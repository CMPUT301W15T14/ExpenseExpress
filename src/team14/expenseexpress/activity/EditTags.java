package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.activity.TagListDialogFragment.TagsListAdapter;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.TagListController;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import team14.expenseexpress.model.TagList;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EditTags extends ExpenseExpressActivity {
	private EditTags activity;

	private ListView tagListView;
	private ArrayAdapter<ClaimTag> tagAdapter;
	TagListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tags);
		
		final ListView taglistview = (ListView) findViewById(R.id.editTagList);

		adapter = new TagListAdapter(this);
		taglistview.setAdapter(adapter);
	}
	@SuppressLint("ValidFragment")
	private class NewTagDialogFragment extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Dialog dialog = super.onCreateDialog(savedInstanceState);

			// request a window without the title
			dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			return dialog;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View view = inflater.inflate(R.layout.activity_add_tag,
					container, false);
			view.findViewById(R.id.addTagButton).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							String text = ((EditText) view
									.findViewById(R.id.newTagNameField))
									.getText().toString();
							if (text.length() == 0) {
								toast("Tag name can't be empty");
							} else if (TagListController.getInstance().getTagList().getTags().contains(new ClaimTag(text))) {
									toast("Already in list");
									return;
								}
								// if all good, add to list and dismiss dialog
								TagListController.getInstance().addTag(new ClaimTag(text));
								toast("Added to list");
								updateTagsListAdapter(adapter);
								dismiss();
							}
					});
			return view;
		}
	}
	
	public void addNewTags(View v) {
		FragmentManager fm = getFragmentManager();
		new NewTagDialogFragment().show(fm, "newTagDialogFragment");
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	public void acceptTags(View v) {
		finish();
	}
	
	private void updateTagsListAdapter(TagListAdapter adapter) {
		this.adapter = adapter;
		adapter.notifyDataSetChanged();
	}
	
	/*public void setTagsListAdapter(TagsListAdapter adapter) {
		this.tagsListAdapter = adapter;
	}*/
}
