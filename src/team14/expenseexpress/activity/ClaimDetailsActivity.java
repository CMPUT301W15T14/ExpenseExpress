package team14.expenseexpress.activity;

import java.util.ArrayList;

import team14.expenseexpress.R;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

public class ClaimDetailsActivity extends Activity {

	    private ArrayList<Claim> claimList;
	    private ClaimListController cListController;
	    private ArrayList<Claim> claims;
	    private LocalFileHelper helper;
	    private LayoutInflater inflater;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {//copied from ClaimListActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);
        inflater = LayoutInflater.from(this);
        
        // TODO
        cListController = ClaimListController.getInstance();
        cListController.initialize(this);
        claimList = ClaimList.getInstance().getClaims();
    }
    
    
    
    
}