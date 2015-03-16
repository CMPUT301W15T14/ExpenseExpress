package team14.expenseexpress.activity;

import java.util.ArrayList;

//import com.example.assignment1.Globals;


import team14.expenseexpress.ExpenseExpressActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimList;
import team14.expenseexpress.util.LocalFileHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * View
 * ClaimDetailsActivity Class:
 * This view is for the Claim Details page of the app.
 * It allows the user to see the name, start and end date, status, 
 * destination, approvers, and Total spent.
 */
public class ClaimDetailsActivity extends ExpenseExpressActivity {

	    private ArrayList<Claim> claimList;
	    private ClaimController cListController;
	    private ArrayList<Claim> claims;
	    private LocalFileHelper helper;
	    private Claim claim;
	    private LayoutInflater inflater;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);
        
        // TODO
        cListController = ClaimController.getInstance();
        cListController.initialize(this);
        
        claim = cListController.getSelectedClaim();
        inflater = LayoutInflater.from(this);
        
        claimList = ClaimList.getInstance().getClaims();
        updateClaimInfo(null);
    }
    
    private void updateClaimInfo(View v){
    	//inputs TextView Strings
    	final TextView claimName = (TextView) findViewById(R.id.detailsName);
    	claimName.setText(claim.getName());
    	final TextView claimStart = (TextView) findViewById(R.id.detailsStartDate);
    	claimStart.setText(claim.startDateToString()); 
    	final TextView claimEnd = (TextView) findViewById(R.id.detailsEndDate);
    	claimEnd.setText(claim.endDateToString()); 
    	final TextView claimStatus = (TextView) findViewById(R.id.claimDetailsStatus);
    	claimName.setText(claim.getStatus());
    	//inputs ListView Data
    	
		final ListView destinationListView = (ListView) (findViewById(R.id.claimListofDestinations));
		destinationListView.setAdapter(new BaseAdapter(){
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return claim.getDestinations().size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder viewHolder;
				if (convertView == null){//if blank row
					convertView = inflater.inflate(R.layout.row_destination, parent,false);
					viewHolder = new ViewHolder();
					viewHolder.name = (TextView) convertView.findViewById(R.id.destinationName);
					viewHolder.reason = (TextView) convertView.findViewById(R.id.destinationReason);
					convertView.setTag(viewHolder);
				}
				else{
					viewHolder = (ViewHolder)convertView.getTag();
				}
				
		    	viewHolder.name.setText(claim.getDestinations().get(position).getDestination());
		    	viewHolder.reason.setText(claim.getDestinations().get(position).getReason());
				return convertView;
			}
			class ViewHolder{
				TextView name,reason;
			}
		});
		
		final ListView expenseListView = (ListView) (findViewById(R.id.claimListofCurrency));
		expenseListView.setAdapter(new BaseAdapter(){
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return claim.getExpenseList().size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder viewHolder;
				if (convertView == null){//if blank row
					convertView = inflater.inflate(R.layout.row_expense, parent,false);
					viewHolder = new ViewHolder();
					viewHolder.purchase = (TextView) convertView.findViewById(R.id.expenseItem);
					viewHolder.cost = (TextView) convertView.findViewById(R.id.expenseCost);
					convertView.setTag(viewHolder);
				}
				else{
					viewHolder = (ViewHolder)convertView.getTag();
				}
				
		    	viewHolder.purchase.setText(claim.getExpenseList().get(position).getName());
		    	viewHolder.cost.setText(claim.getExpenseList().get(position).getAmount().getNumber()+claim.getExpenseList().get(position).getAmount().getCurrency().toString()); //does not 
				return convertView;
			}
			class ViewHolder{
				TextView purchase,cost;
			}
		});
		

    }
    //still needs add approver sh*t
    
    
    
    
}
