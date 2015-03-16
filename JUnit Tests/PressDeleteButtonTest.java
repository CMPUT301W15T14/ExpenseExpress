package team14.expenseexpress.tests;

import junit.framework.TestCase;

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
private void testDeleteButton() {
Claim claim = new Claim(); //starts out editable
claim.canEdit(false);
((Button) activity.findViewById(team14.teamproject.test.R.id.deleteButton)).performClick();//click delete button
assertEquals("The edit button should be pressable",claim.getEdit(),false); //also, if claim purely remains to exist, test is proved
((Button) activity.findViewById(team14.teamproject.test.R.id.editButton)).performClick();
try{
assertTrue("You should not see this text. Object should not exist",claim.deleteEdit());
}
catch(ObjectDeletedException e){
logger.error("claim object has been deleted. This is a good thing. Be happy.");
}
}
}