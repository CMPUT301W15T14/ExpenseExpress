import team14.teamproject.test.R;
import team14.teamproject.ClaimSummaryActivity;
import android.test.ActivityInstrumentationTestCase2;

public class ClaimDataInput extends ActivityInstrumentationTestCase2<Claim> {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
		public void testClaimName(){
		Claim claim = new Claim();
		String nameIn = "name";
		claim.name(nameIn);
		assertEquals ("The name is saved", nameIn, claim.getName()); 
	}
		public void testClaimDateStart(){
		Claim claim = new Claim();
		String dateIn = "10-11-12";
		claim.startDate(dateIn);
		assertEquals ("The start date is saved", dateIn, claim.getStartDate()); 
	}
	public void testClaimDateEnd(){
		Claim claim = new Claim();
		String dateIn = "12-11-12";
		claim.endDate(dateIn);
		assertEquals ("The start date is saved", dateIn, claim.getEndDate()); 
	}
}
