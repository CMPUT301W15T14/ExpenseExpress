import team14.teamproject.test.R;
import team14.teamproject.ClaimSummaryActivity;
import android.test.UiThreadTest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;

public class ClaimSummaryActivityTest extends
		ActivityInstrumentationTestCase2<ClaimSummaryActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
	}
	
	private void testEditButton() {
		Claim claim = new Claim(); //starts out editable
		((Button) activity.findViewById(team14.teamproject.test.R.id.editButton)).performClick();//click Edit Button
		assertTrue("The edit button should be pressable",claim.getEdit());
		claim.canEdit(false);
		((Button) activity.findViewById(team14.teamproject.test.R.id.editButton)).performClick();
		assertEquals("The edit button should not be pressable",claim.getEdit(),false);
		}
}
