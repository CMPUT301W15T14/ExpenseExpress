package team14.expenseexpress;

import java.util.ArrayList;

import team14.expenseexpress.activity.ExpenseDetailsActivity;
import team14.expenseexpress.activity.ExpenseEditActivity;
import team14.expenseexpress.activity.ExpenseListActivity;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.model.Claim;

import android.app.Activity;
import android.app.AlertDialog;
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

public class ApproverAdapter extends BaseAdapter {

	private Activity activity;
	private static ArrayList<Claim> claimList;
	private LayoutInflater inflater;
	public Resources resources;
	Claim claim;

	public ApproverAdapter(Activity activity, ArrayList<Claim> claims) {
		this.claimList = claims;
		this.activity = activity;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			view = inflater.inflate(R.layout.custom_claim_list, null);
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
			view.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View arg0) {
					AlertDialog.Builder adb = new AlertDialog.Builder(activity);
					adb.setMessage("Menu of " + claim.getName());
					adb.setCancelable(true);
					adb.setPositiveButton("1",new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}

					});
					adb.setNeutralButton("2",new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}

					});
					adb.setNegativeButton("3",new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}

					});
					return false;
				}
			});
		}
		return view;
	}
}



