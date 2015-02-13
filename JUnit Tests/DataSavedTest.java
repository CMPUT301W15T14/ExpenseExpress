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
	
			private void testSaveData(){
			Claim claim = new Claim();
			String nameIn = "Steven"
			claim.name(nameIn);
			Process.KillProcess(Process.claimApp());//claimApp being the name of the app
			open(claimApp());//open function does not exist... Don't know how exactly to do this. Google proved useless
			try{
				assertEqual("The claim saved the data!",nameIn,claim.getName());
			}
			catch(ObjectDeletedException e){
				logger.error("claim object does not exist. This is not good.");
		}
}
