package team14.expenseexpress;

import java.util.ArrayList;

import team14.expenseexpress.activity.ClaimDetailsActivity;
import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.util.ElasticSearchHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ApproverAdapter extends BaseAdapter {
	
	private Activity activity;
	private static ArrayList<Claim> claimList;
	private ArrayList<Claim> tempList;
	private LayoutInflater inflater;
	Claim claim;
	
	
	public ApproverAdapter(Activity activity, ArrayList<Claim> claims) {
		this.activity = activity;
		claimList = claims;
		inflater = LayoutInflater.from(activity);

	}
	
	@Override
	public int getCount() {
		if(claimList.size() <=0) {
			return 1;
		}
		return claimList.size();
	}

	@Override
	public Claim getItem(int position) {
		return claimList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder{
		public TextView claimantName;
		public TextView startDate;
		public TextView destinations;
		public TextView claimStatus;
		public TextView totalAmount;
		public TextView approverName;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;

		if(convertView == null) {
			view = inflater.inflate(R.layout.approver_claim_list, null);
			holder = new ViewHolder();
			holder.claimantName = (TextView) view.findViewById(R.id.claimantTextView);
			holder.startDate = (TextView) view.findViewById(R.id.startDateTextView);
			holder.destinations = (TextView) view.findViewById(R.id.destinationsTextView);
			holder.claimStatus = (TextView) view.findViewById(R.id.claimStatusTextView);
			holder.totalAmount = (TextView) view.findViewById(R.id.totalAmountTextView);
			holder.approverName = (TextView) view.findViewById(R.id.approverTextView);

			view.setTag(holder);
		} else {
			holder=(ViewHolder)view.getTag();
		}

		if(claimList.isEmpty()) {
			//No claims to show.
		} else {
			claim = null;
			claim = (Claim) claimList.get(position);

			holder.claimantName.setText(claim.getClaimant().getName());
			holder.startDate.setText(claim.startDateToString());
			holder.destinations.setText(claim.destinationsToString());
			holder.claimStatus.setText(claim.getStatus());
			holder.totalAmount.setText(claim.totalAmountToString());

			if(claim.getApprover().getName() == null) {
				holder.approverName.setText("Not Approved Yet");
			} else {
				holder.approverName.setText(claim.getApprover().getName());
			}

			view.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View arg0) {
					AlertDialog.Builder adb = new AlertDialog.Builder(activity);
					adb.setMessage("Menu of " + claim.getName());
					adb.setCancelable(true);
					adb.setNeutralButton("Details",new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							activity.startActivity(new Intent(activity, ClaimDetailsActivity.class)); 

						}

					});
					adb.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();

						}

					});
					return false;
				}
			});

			view.setOnClickListener(new OnItemClickListener( position ));

		}
		return view;
	}
	
	
	@SuppressWarnings("unused")
	private class OnItemClickListener  implements OnClickListener {          
        private int mPosition;
         
        OnItemClickListener(int position){
             mPosition = position;
        }
         
        @Override
        public void onClick(View arg0) {

   
          ClaimListActivity sct = (ClaimListActivity)activity;

         /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            sct.onItemClick(claimList.get(mPosition));
        }              
    }
	
	
	private class GetSubmittedSync extends AsyncTask<Void, Void, Void> {
		private GetSubmittedSync() {
			tempList = new ArrayList<Claim>();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			tempList.addAll(ElasticSearchHelper.getInstance().getSubmitted());;
			return null;
		}
		
		protected void onPostExecute(Void result) {
			mergeLists();
		}
		
	}
	
	private void mergeLists() {
		claimList.addAll(tempList);
		notifyDataSetChanged();
	}
	
	public void getSubmittedClaims() {
		GetSubmittedSync task = new GetSubmittedSync();
		task.execute();
	}
}



