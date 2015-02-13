import team14.teamproject.test.R;
import team14.teamproject.ClaimSummaryActivity;
import android.test.UiThreadTest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;

public class ClaimViewTest extends
		ActivityInstrumentationTestCase2<ClaimSummaryActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		textInput = ((EditText) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.body));
	}
		public void testClaimView(){
		Claim claim = new Claim();
		String nameIn = "name";
		String place = "Colorado";
		String reason = "business";
		claim.name(nameIn);
		claim.dest(place);
		claim.destReason(reason);
		instrumentation.runOnMainSync(new Runnable(){
		@Override
		public void run(){
		textInput.setText(claim.getInfo()); //returns all inputted data
		}
		});
		instrumentation.waitForIdleSync();
		assertEquals("Does this work?", nameIn+" "+place+" "+reason, textInput.getText().toString()); //assuming this is the orientation of presentation
	}
}
	
