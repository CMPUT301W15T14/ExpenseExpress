package team14.expenseexpress.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import team14.expenseexpress.App;
import team14.expenseexpress.R;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.ClaimTag;
import team14.expenseexpress.model.User;

/**
 * 2) Claim List Screen

 This screen enables the user to:

 a) Search claims by tag using the search field

 b) Add a new claim by clicking the Add New Claim Button

 c) Look at current tags by clicking the Look at Tags Button

 d) Edit/Delete/Look at Status of a claim by long clicking on a claim and bringing up a screen prompting the user for a choice

 e) Bring up the expense list of a claim by clicking on it All relevant information, according to the specifications, for a claim will be shown in the list.
 */

public class ClaimListActivity extends ActionBarActivity {

    private EditText editText_tagSearch;
    private ArrayList<ClaimTag> claimTags;
    private ArrayList<ClaimTag> chosenTags;
    private ArrayList<Claim> claims;
    private App expenseExpressApplication;
    private long id;
    private App application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_list);
        application = (App) getApplication();
        //id = getIntent().getLongExtra(application.KEY, Username.LOCAL_ONLY);
        expenseExpressApplication = (App) getApplication();
    }

    protected void onResume(){
        super.onResume();
        showClaimTagChoices();
        showClaims();
    }

    private void showClaims() {
        ArrayList<Claim> filteredClaims = new ArrayList<>();
        for (int i = 0; i<claims.size(); i++){
            ArrayList<ClaimTag> tags = claims.get(i).getTags();
            for (int j = 0; j<tags.size(); j++){
                if (chosenTags.contains(tags.get(j))){
                    filteredClaims.add(claims.get(i));
                }
            }
        }
        // TODO listadapter on filteredClaims
        // with listeners that call onClick_openExpenseList, onLongClick_editClaim,
        // onLongClick_deleteClaim, onLongClick_lookAtStatus
    }

    private void showClaimTagChoices() {
        showAllTagChoices();
        editText_tagSearch = (EditText) findViewById(R.id.tagSearch);
        editText_tagSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                filterClaimTagChoices(search);
            }

            private void filterClaimTagChoices(String search) {
                // TODO (also, set onItemClickListener in this list to update mChosenTags)
            }
        });
    }

    private void showAllTagChoices() {
        // TODO
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.claim_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick_newClaim(View view){
        // TODO
    }

    public void onClick_lookAtTags(View view){
        // TODO
    }

    private void onClick_openExpenseList(View view){
        // TODO
    }

    private void onLongClick_editClaim(View view){
        //  TODO
    }

    private void onLongClick_deleteClaim(View view){
        // TODO
    }

    private void onLongClick_lookAtStatus(View view){
        // TODO
    }

}
