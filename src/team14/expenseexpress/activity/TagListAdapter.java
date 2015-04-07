
package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.TagListController;
import team14.expenseexpress.model.ClaimTag;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
/**
 * A CustomBaseAdapter for claims list. This lets use show destination, status, claim name, date, and tags
 * in the listview
 * Source taken from: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/ -- Jan 31, 2015
 *
 */
public class TagListAdapter extends BaseAdapter {
	
	private static ArrayList<ClaimTag> taglist;
	private LayoutInflater mInflater;

	public TagListAdapter(Context context) {
		taglist = TagListController.getInstance().getTagList().getTags();
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return taglist.size();
	}

	public Object getItem(int position) {
		return taglist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_tag_list, null);
			holder = new ViewHolder();
			holder.tag = (TextView) convertView.findViewById(R.id.AddDestLatitude);
			holder.box = (CheckBox) convertView.findViewById(R.id.approvedBox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.box.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Is the view now checked?
				boolean checked = ((CheckBox) v).isChecked();
				if (checked) {
					ClaimController.getInstance().getSelectedClaim().addTag(taglist.get(position));
				} else {
					ClaimController.getInstance().getSelectedClaim().removeTag(taglist.get(position));
				}
			} 
		}); 
		holder.tag.setText(taglist.get(position).getName());
		
		return convertView;
	}

	static class ViewHolder {
		TextView tag;
		CheckBox box;
		
	}
}