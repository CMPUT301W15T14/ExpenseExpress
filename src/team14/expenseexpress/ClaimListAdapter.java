
package team14.expenseexpress;

import java.util.ArrayList;
import java.util.Collections;

import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.Destination;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
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
	private ArrayList<Claim> tempList;
	private final LayoutInflater mInflater;

	public ClaimListAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		claimList = ClaimController.getInstance().getClaimList().getClaims();
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
		if(tags != null){
			if (tags.isEmpty()){
				filteredClaimList.addAll(claimList);
			} else {
				for (int i = 0; i < claimList.size(); i ++ ){
					for (int j = 0; j < tags.size(); j ++ ){
						if (claimList.get(i).getTags().contains(tags.get(j)) &&
								!filteredClaimList.contains(claimList.get(i))){
							filteredClaimList.add(claimList.get(i));
						}
					}
				}
			}
		} else {
			filteredClaimList.addAll(claimList);
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
			holder.approver = (TextView) convertView.findViewById(R.id.approverNameText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (filteredClaimList.get(position).getApprover().getName() == ""){
			holder.approver.setVisibility(View.INVISIBLE);
		}
		else{
			holder.approver.setVisibility(View.VISIBLE);
			holder.approver.setText(filteredClaimList.get(position).getApprover().getName());
		}
		holder.claim.setText(filteredClaimList.get(position).getName());
		double userlat = UserController.getInstance().getLatitude();
		double userlng = UserController.getInstance().getLongitude();
		try{
		double claimlat = filteredClaimList.get(position).getDestinations().get(0).getLatitude();
		double claimlng = filteredClaimList.get(position).getDestinations().get(0).getLongitude();
		double distance = haversine(claimlat,claimlng,userlat,userlng);
		double ratio = 0;
		ratio = 255 * (1-(distance/20000));

		int color = (int) ratio;
		holder.claim.setTextColor(Color.rgb(color,0,color));}
		catch (Exception e){
			e.printStackTrace();
		}
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


	public  double haversine(double lat1, double lon1, double lat2, double lon2) {
		double R = 6372.8; // In kilometers
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}
	
	private class GetReturnedSync extends AsyncTask<Void, Void, Void> {
		private GetReturnedSync() {
			tempList = new ArrayList<Claim>();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			tempList.addAll(ElasticSearchHelper.getInstance().getSubmitted());
			return null;
		}
		
		protected void onPostExecute(Void result) {
			mergeLists();
		}
		
	}
	
	private void mergeLists() {
		for(Claim c: tempList) {
			if(claimList.contains(c)) {
				claimList.remove(c);
				claimList.add(c);
			}
		}
		updateFilteredClaimList(null);
		notifyDataSetChanged();
	}
	
	public void getReturnedClaims() {
		GetReturnedSync task = new GetReturnedSync();
		task.execute();
	}
	

}
	

