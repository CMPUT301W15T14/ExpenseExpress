package team14.expenseexpress;

import java.util.ArrayList;

import team14.expenseexpress.activity.ClaimDetailsActivity;
import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.activity.ExpenseDetailsActivity;
import team14.expenseexpress.activity.ExpenseEditActivity;
import team14.expenseexpress.activity.ExpenseListActivity;
import team14.expenseexpress.activity.ReturnClaimActivity;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.util.BooleanListener;
import team14.expenseexpress.util.ElasticSearchHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

public class ApproverAdapter extends BaseAdapter implements team14.expenseexpress.util.BooleanListener.Listener {
	
	private Activity activity;
	private static ArrayList<Claim> claimList;
	private LayoutInflater inflater;
	Claim claim;
	
	BooleanListener listener;
	
	public ApproverAdapter(Activity activity) {
		this.claimList = new ArrayList<Claim>();
		this.activity = activity;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listener = new BooleanListener();
		listener.setBooleanListener(listener);
	}
	
	public void getSubmittedClaims(){
		final ProgressDialog ringProgressDialog = ProgressDialog.show(activity, "Please wait ...", "Loading Submitted Claims ...", true);
		ringProgressDialog.setCancelable(true);
		new Thread(new Runnable() {

			@Override
			public void run() { 
				claimList = ElasticSearchHelper.getInstance().getSubmitted();
				ringProgressDialog.dismiss();
			}
		}).start();
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

		if(claimList.size() <=0) {
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
			view.setOnClickListener(new OnItemClickListener( position ));
		}
		return view;
	}
	
	
	@SuppressWarnings("unused")
	private class OnItemClickListener  implements OnClickListener{          
        private int mPosition;
         
        OnItemClickListener(int position){
             mPosition = position;
        }
         
        @Override
        public void onClick(View arg0) {

   
          ClaimListActivity sct = (ClaimListActivity)activity;

         /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            sct.onItemClick(mPosition);
        }              
    }

	@Override
	public void onStateChange(boolean state) {
		if(state) {
			Toast.makeText(activity, "Data Loaded", Toast.LENGTH_SHORT).show();
			notifyDataSetChanged();
		} else {
			Toast.makeText(activity, "Boolean Off", Toast.LENGTH_SHORT).show();
		}
	} 
	
}



