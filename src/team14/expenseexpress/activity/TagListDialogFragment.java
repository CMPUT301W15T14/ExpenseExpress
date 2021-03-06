package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.TagListController;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * Dialog Fragment for the claimant when accessing tags. The user can add new tags and choose which tags to use to filter the list of expense claims in ClaimListActivity.
 * @author  Team 14
 * @date  April 6, 2015
 * @version  1.5
 */ 
@SuppressLint("ValidFragment")
public class TagListDialogFragment extends android.app.DialogFragment {
	/**
	 */
	private final ClaimListActivity activity;
	private static final int CHECKBOX_SIZE_IN_SP = 25;
	
	

	@Override
	public void onStop() {
		super.onStop();
		activity.filterClaimsByTags(TagListController.getInstance().getChosenTags().getTags());		
	}
/**
 * Constructor
 * @param activity
 */
	public TagListDialogFragment(ClaimListActivity activity) {
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
		v.findViewById(R.id.addNewTagButton)
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						activity.showNewTagDialog();
					}
				});
		v.findViewById(R.id.button_confirm).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				dismiss();		
			}
			
		});
		((ListView) v.findViewById(R.id.ExpenseList)).setAdapter(adapter);
		activity.setTagsListAdapter(adapter);
		return v;
	}

	/**
	 * BaseAdapter implementation for the list of tags.
	 */
	public class TagsListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return TagListController.getInstance().getTagList().getTags().size();
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
			final TagListController tagController = TagListController.getInstance();

			CheckBox checkBox = (CheckBox) convertView;
			if (checkBox == null) {
				checkBox = new CheckBox(activity);
			}

			if (tagController.getChosenTags().getTags().contains(tagController.getTagList().get(position))) {
				checkBox.setChecked(true);
			}

			checkBox.setTextColor(getResources().getColor(R.color.white));
			checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, CHECKBOX_SIZE_IN_SP);
			checkBox.setText(tagController.getTagList().get(position).getName());

			checkBox.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// Is the view now checked?
					boolean checked = ((CheckBox) v).isChecked();
					if (checked) {
						tagController.getChosenTags().getTags().add(tagController.getTagList().get(position));
					} else {
						tagController.getChosenTags().getTags().remove(tagController.getTagList().get(position));
					}
				} 
			}); 

			return checkBox;
		} 
	}
}
