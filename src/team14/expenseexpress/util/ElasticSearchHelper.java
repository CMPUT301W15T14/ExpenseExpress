package team14.expenseexpress.util;

import android.content.Context;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressApplication;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Username;

public class ElasticSearchHelper {
    private Context context;
    private GsonHelper gsonHelper;
    private ArrayList<Username> localUsernames;
    private ArrayList<Claim> localClaims;
    
    public ElasticSearchHelper(ExpenseExpressApplication expenseExpressApplication, GsonHelper gsonHelper) {
        this.context = expenseExpressApplication;
        this.gsonHelper = gsonHelper;
    }

    public void synchronizeUsernames() {
        localUsernames = gsonHelper.loadUsernames();
        ArrayList<Username> serverUsernames = getServerUsernames();
        synchronizeUsernames(localUsernames, serverUsernames);
    }

    private void synchronizeUsernames(ArrayList<Username> localUsernames, ArrayList<Username> serverUsernames) {
        // TODO (including assigning new ID numbers to new User(s) who don't have IDs by the server which holds an autoincrement value)
        ArrayList<Username> ultimateUsernames = null;
        gsonHelper.save(ultimateUsernames, GsonHelper.FILENAME_USERNAMES);
        uploadToServer(ultimateUsernames);
    }

    private void uploadToServer(ArrayList<Username> ultimateUsernames) {
        // TODO
    }

    public ArrayList<Username> getServerUsernames() {
        // TODO
        return null;
    }

    public void synchronizeClaims(long id) {
        localClaims = gsonHelper.loadClaims(Long.valueOf(id).toString() + GsonHelper.FILENAME_CLAIMS_SUFFIX);

    }
}