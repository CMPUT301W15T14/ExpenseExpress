import team14.teamproject.test.R;
import team14.teamproject.ClaimSummaryActivity;
import android.test.ActivityInstrumentationTestCase2;

public class ClaimDestinationTest extends ActivityInstrumentationTestCase2<Claim> {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
		public void testClaimDestination(){
		Claim claim = new Claim();
		String place = "Amsterdam";
		String reason = "Business";
		claim.dest(place);
		claim.destReason(reason);
		assertEquals("Destination is saved",place,claim.getDest());
		assertEquals("Destination reason is saved",reason,getDestReason());
	}
