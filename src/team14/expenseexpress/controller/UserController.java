package team14.expenseexpress.controller;

import java.util.ArrayList;

import team14.expenseexpress.ExpenseExpressApplication;
import team14.expenseexpress.model.Username;

public class UserController {

    private ExpenseExpressApplication application;
    private ArrayList<Username> usernames;

    public UserController(ExpenseExpressApplication application) {
        this.application = application;
    }


    public long getId(String name) {
        for (int i = 0; i<usernames.size(); i++){
            if (usernames.get(i).getName() == name){
                return usernames.get(i).getId();
            }
        }
        return Username.NOT_IN_LIST;
    }
}