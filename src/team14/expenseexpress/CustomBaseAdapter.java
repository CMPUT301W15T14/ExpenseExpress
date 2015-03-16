
package team14.expenseexpress;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * A CustomBaseAdapter for claims list. This lets use show destination, status, claim name, date, and tags
 * in the listview
 * Source taken from: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/ -- Jan 31, 2015
 *
 */
public class CustomBaseAdapter extends BaseAdapter {
	
	private static ArrayList<Claim> ClaimList;
	private LayoutInflater mInflater;

	public CustomBaseAdapter(Context context, ArrayList<Claim> claimlist) {
		ClaimList = claimlist;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return ClaimList.size();
	}

	public Object getItem(int position) {
		return ClaimList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_claim_list, null);
			holder = new ViewHolder();
			holder.claim = (TextView) convertView.findViewById(R.id.claimname);
			holder.startdate = (TextView) convertView.findViewById(R.id.date);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			holder.tags = (TextView) convertView.findViewById(R.id.tags);
			holder.destination = (TextView) convertView.findViewById(R.id.destination);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.claim.setText(ClaimList.get(position).getName());
		holder.startdate.setText(ClaimList.get(position).getStartDateString());
		holder.status.setText(ClaimList.get(position).getStatus());
		//holder.tags.setText(ClaimList.get(position).getTags().get(0).getName());
		//holder.destination.setText(ClaimList.get(position).getDestinations().get(0).getDestination());
		
		return convertView;
	}

	static class ViewHolder {
		TextView claim;
		TextView startdate;
		TextView status;
		TextView tags;
		TextView destination;
	}
}