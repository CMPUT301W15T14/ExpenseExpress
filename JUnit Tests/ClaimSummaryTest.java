import java.util.GregorianCalendar;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;


public class ClaimSummaryTest extends ActivityInstrumentationTestCase2<ClaimSummaryActivity> {
	private String claimant;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private Destination[] destinations; //Destination object includes location and reason.
	private String claimStatus;
	private Amount[] amounts; // Amount object includes value and currency type.
	
	private Claim claim;
	
	public ClaimSummaryTest() {
		super(ClaimSummaryActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		claimant = "Claimer";
		startDate = new GregorianCalendar(2015,1,1);
		endDate = new GregorianCalendar(2015,1,7);
		destinations = new Destination[]{new Destination("Rome", "Just because."), new Destination("Paris","For a baguette.")};
		claimStatus = "Submitted";
		amounts = new Amount[]{new Amount((int)20,"CAN"),new Amount((int)50,"EUR"), new Amount((int)100, "JPN")};
		
		claim = new Claim(claimant, startDate, endDate, destinations, claimStatus, amounts);
	}
	
	public void testSummaryActivity() 
	{
	Claim compareClaim = new Claim(claimant, startDate, endDate, destinations, claimStatus, amounts);
	ClaimSummaryActivity activity = returnClaimSummaryActivity(claim);
    	Controller claimController = activity.getController();
    	Claim getClaim = claimController.getClaim();
    	
    	assertTrue(compareClaim.equals(getClaim)); //Claim class must override Object.equals()
	}
	
	private ClaimSummaryActivity returnClaimSummaryActivity(Claim claim)
    {
    	Intent intent = new Intent();
    	//Claim class must implement Serializable, use Claim claim# = (Claim) getIntent().getSerializableExtra("claim#"); to receive within Activity.
    	intent.putExtra(ClaimSummaryActivity.CLAIM, claim);
    	setActivityIntent(intent);
    	return (ClaimSummaryActivity) getActivity();
    } 
	
}
