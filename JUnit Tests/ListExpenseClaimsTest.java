import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

public class ListExpenseClaimsTest extends ActivityInstrumentationTestCase2<ClaimListActivity>{
	
	private GregorianCalendar startDate1 = new GregorianCalendar(2015,1,10);
	private GregorianCalendar startDate2 = new GregorianCalendar(2015,1,5);
	private GregorianCalendar startDate3 = new GregorianCalendar(2015,2,7);
	private String[] destinations1 = new String[]{"Calgary","Edmonton"};
	private String[] destinations2 = new String[]{"Toronto"};
	private String[] destinations3 = new String[]{"London"};
	private String claimStatus = "In Progress";
	private String[] tags = new String[]{};
	private int amount1 = 20;
	private int amount2 = 40;
	private int amount3 = 60;
	private String currency1 = "CAN";
	private String currency2 = "JPN"; //Apparently Toronto consists of Japanese seperatists...
	private String currency3 = "EUR";
	
	
	public ListExpenseClaimsTest() {
        super(ClaimListActivity.class);
    }
    
    protected void setUp() throws Exception {
    	super.setUp();	
    }
	
    public void testClaimList() 
    {
    	Claim claim1 = new Claim(startDate1, destinations1, claimStatus, tags, amount1, currency1);
    	Claim claim2 = new Claim(startDate2, destinations2, claimStatus, tags, amount2, currency2);
    	Claim claim3 = new Claim(startDate3, destinations3, claimStatus, tags, amount3, currency3);
    	
    	ClaimListActivity activity = returnClaimListActivity(claim1, claim2, claim3);
    	Controller claimController = ArrayList<Claim>(activity.getController());
    	ArrayList<Claim> claimList = claimController.getClaimList();
    	
    	//Sorting will require implementing Comparable within the Claim class.
    	assertEquals("position = 0, claim should be claim2", claimList.get(0), claim2);
    	assertEquals("position = 1, claim should be claim1", claimList.get(1), claim1);
    	assertEquals("position = 2, claim should be claim3", claimList.get(2), claim3);
    }
    
    public void testClaimListView() 
    {
    	Claim claim1 = new Claim(startDate1, destinations1, claimStatus, tags, amount1, currency1);
    	Claim claim2 = new Claim(startDate2, destinations2, claimStatus, tags, amount2, currency2);
    	Claim claim3 = new Claim(startDate3, destinations3, claimStatus, tags, amount3, currency3);
    	
    	ClaimListActivity activity = returnClaimListActivity(claim1, claim2, claim3);
    	
    	//onItemClickListener required for ListView inside ClaimListActivity.
    	assertEquals("position = 0, claim should be claim2", activity.onItemClick(0), claim2);
    	assertEquals("position = 1, claim should be claim1", activity.onItemClick(1), claim1);
    	assertEquals("position = 2, claim should be claim3", activity.onItemClick(2), claim3);
    	
    }
    
    
    
    private ClaimListActivity returnClaimListActivity(Claim claim1, Claim claim2, Claim claim3)
    {
    	Intent intent = new Intent();
    	//Claim class must implement Serializable, use Claim claim# = (Claim) getIntent().getSerializableExtra("claim#"); to receive within Activity.
    	intent.putExtra(ClaimListActivity.CLAIM_1, claim1);
    	intent.putExtra(ClaimListActivity.CLAIM_2, claim2);
    	intent.putExtra(ClaimListActivity.CLAIM_3, claim3);
    	setActivityIntent(intent);
    	return (ClaimListActivity) getActivity();
    } 
}