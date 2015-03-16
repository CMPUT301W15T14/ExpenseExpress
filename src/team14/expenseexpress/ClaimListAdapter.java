
package team14.expenseexpress;

import java.util.ArrayList;
import java.util.EmptyStackException;

import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
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
public class ClaimListAdapter extends BaseAdapter {
	
	private static ArrayList<Claim> ClaimList;
	private LayoutInflater mInflater;

	public ClaimListAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		ClaimList = ClaimController.getInstance().getClaimList().getClaims();
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
		
		ClaimList = ClaimController.getInstance().getClaimList().getClaims();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_claim_list, null);
			holder = new ViewHolder();
			holder.claim = (TextView) convertView.findViewById(R.id.claimname);
			holder.startdate = (TextView) convertView.findViewById(R.id.startdate);
			holder.enddate = (TextView) convertView.findViewById(R.id.enddate);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			holder.tags = (TextView) convertView.findViewById(R.id.tags);
			holder.destination = (TextView) convertView.findViewById(R.id.destination);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.claim.setText(ClaimList.get(position).getName());
		holder.startdate.setText(ClaimList.get(position).getStartDateString());
		holder.enddate.setText(ClaimList.get(position).getEndDateString());
		holder.status.setText(ClaimList.get(position).getStatus());
		String tags = new String("");
		String destinations = new String("");
		try {
			for(ClaimTag tag: ClaimList.get(position).getTags()) {
				tags += ("  " +tag.getName() +",");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		try {
			for(Destination destination: ClaimList.get(position).getDestinations()) {
				destinations += ("  " +destination.getDestination());
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		holder.tags.setText(tags);
		holder.destination.setText(destinations);
		
		return convertView;
	}

	static class ViewHolder {
		TextView enddate;
		TextView claim;
		TextView startdate;
		TextView status;
		TextView tags;
		TextView destination;
	}
}