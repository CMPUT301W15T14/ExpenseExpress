
package team14.expenseexpress;

import java.util.ArrayList;
import java.util.Collections;

import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import team14.expenseexpress.util.ElasticSearchHelper;
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
	
	private static ArrayList<Claim> claimList;
	private static ArrayList<Claim> filteredClaimList;
	private final LayoutInflater mInflater;

	public ClaimListAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		switch(Mode.get()) {
		case Mode.APPROVER :
			claimList = new ArrayList<Claim>();
			break;
		case Mode.CLAIMANT :
			claimList = ClaimController.getInstance().getClaimList().getClaims();
			break;
		}
		filteredClaimList = new ArrayList<Claim>();
		filteredClaimList.addAll(claimList);
	}

	/**
	 * (Re)builds filteredClaimList filtered by chosen tags.
	 *  
	 * @param tags	If no tags are chosen, then all Claims are displayed.
	 */
	public void updateFilteredClaimList(ArrayList<ClaimTag> tags){
		filteredClaimList.clear();
		if (tags.size() == 0){
			filteredClaimList.addAll(claimList);
		}
		for (int i = 0; i < claimList.size(); i ++ ){
			for (int j = 0; j < tags.size(); j ++ ){
				if (claimList.get(i).getTags().contains(tags.get(j)) &&
						!filteredClaimList.contains(claimList.get(i))){
					filteredClaimList.add(claimList.get(i));
				}
			}
		}
		Collections.sort(filteredClaimList, new Claim.ClaimComparator());
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return filteredClaimList.size();
	}

	@Override
	public Object getItem(int position) {
		return filteredClaimList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		// claimList = ClaimController.getInstance().getClaimList().getClaims();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_claim_list, null);
			holder = new ViewHolder();
			holder.claim = (TextView) convertView.findViewById(R.id.claimname);
			holder.startdate = (TextView) convertView.findViewById(R.id.startdate);
			holder.enddate = (TextView) convertView.findViewById(R.id.enddate);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			holder.tags = (TextView) convertView.findViewById(R.id.tags);
			holder.destination = (TextView) convertView.findViewById(R.id.destination);
			holder.costs = (TextView) convertView.findViewById(R.id.costs);
			holder.approver = (TextView) convertView.findViewById(R.id.approverName);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(Mode.get() == Mode.APPROVER) {
			//holder.claim.setText(filteredClaimList.get(0).getApprover().getName());
		} else {
			holder.approver.setVisibility(View.INVISIBLE);
		}
		holder.claim.setText(filteredClaimList.get(position).getName());
		holder.startdate.setText(filteredClaimList.get(position).getStartDateString());
		holder.enddate.setText(filteredClaimList.get(position).getEndDateString());
		holder.status.setText(filteredClaimList.get(position).getStatus());
		int i = 0;
		String text =  new String();
		if (filteredClaimList.get(position).getTotalAmounts().size() != 0) {
			while (i < filteredClaimList.get(position).getTotalAmounts().size()){
				if (i == 0)
					text += filteredClaimList.get(position).getTotalAmounts().get(i);
				else{
					text += " ," + filteredClaimList.get(position).getTotalAmounts().get(i);
			}
				i++;
			}
			holder.costs.setText(text);
			
		}
		else {
			holder.costs.setText("No Costs");
		}
		String tags = new String("");
		String destinations = new String("");
		try {
			for(ClaimTag tag: filteredClaimList.get(position).getTags()) {
				tags += ("  " +tag.getName() +",");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		try {
			for(Destination destination: filteredClaimList.get(position).getDestinations()) {
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
		TextView costs;
		TextView approver;
	}

	public void setApproverClaimList() {
		ElasticSearchHelper.getInstance().getSubmitted(claimList);
		filteredClaimList.addAll(claimList);
	}
}