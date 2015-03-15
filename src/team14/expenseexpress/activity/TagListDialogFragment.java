package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.TagList;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

public class TagListDialogFragment extends android.app.DialogFragment {
	private final TagList tagList;
	private final ClaimListActivity activity;

	public TagListDialogFragment(ClaimListActivity activity) {
		tagList = TagList.getInstance(activity);
		this.activity = activity;
	}

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

		View v = inflater.inflate(R.layout.activity_tags, container, false);
		TagsListAdapter adapter = new TagsListAdapter();
		(v.findViewById(R.id.addNewTagButton))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						activity.showNewTagDialog();
					}
				});
		((ListView) v.findViewById(R.id.ExpenseList)).setAdapter(adapter);
		activity.setTagsListAdapter(adapter);
		return v;
	}

	public class TagsListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tagList.size();
		}

		@Override
		public Object getItem(int arg0) {
			/* not needed */
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			/* not needed */
			return 0;
		}

		@Override
		public View getView(final int position, final View convertView,
				ViewGroup parent) {
			// Recycles convertView
			// TODO: put controller in between View and Model
			final ClaimTag tag = tagList.get(position);

			CheckBox checkBox = (CheckBox) convertView;
			if (checkBox == null) {
				checkBox = new CheckBox(activity);
			}

			if (activity.getChosenTags().contains(tag)) {
				checkBox.setChecked(true);
			}

			checkBox.setText(tag.getName());

			checkBox.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// Is the view now checked?
					boolean checked = ((CheckBox) v).isChecked();
					if (checked) {
						activity.getChosenTags().add(tag);
					} else {
						activity.getChosenTags().remove(tag);
					}
				}
			});

			return checkBox;
		}
	}
}
