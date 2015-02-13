

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ca.ualberta.cs.expenseclaims.Expense;
import ca.ualberta.cs.expenseclaims.ExpenseListActivity;

import Claim;
import ClaimListActivity;
import Controller;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

public class ListExpensesTest extends ActivityInstrumentationTestCase2<ExpenseListActivity>{

	private GregorianCalendar expenseDate1 = new GregorianCalendar(2015,1,10);
	private GregorianCalendar expenseDate2 = new GregorianCalendar(2015,1,5);
	private GregorianCalendar expenseDate3 = new GregorianCalendar(2015,2,7);
	private String[] category = new String[]{};
	private String description1 = "description1";
	private String description2 = "description2";
	private String description3 = "description3";
	private String claimStatus = "In Progress";
	private int amount1 = 20;
	private int amount2 = 40;
	private int amount3 = 60;
	private String currency1 = "CAN";
	private String currency2 = "JPN"; //Apparently Toronto consists of Japanese seperatists...
	private String currency3 = "EUR";
	private boolean photoReceipt = true;
	
	private Expense ex1;
	private Expense ex2;
	private Expense ex3;
	private ExpenseListActivity activity;
	
	public ListExpensesTest() {
        super(ExpenseListActivity.class);
    }
    
    protected void setUp() throws Exception {
    	super.setUp();	
    	ex1 = new Expense(expenseDate1, description1, claimStatus, category, amount1, currency1, photoReceipt);
    	ex2 = new Expense(expenseDate2, description2, claimStatus, category, amount2, currency2, photoReceipt);
    	ex3 = new Expense(expenseDate3, description3, claimStatus, category, amount3, currency3, photoReceipt);
    }
    
    public void testExpenseList() 
    {
    	
    	ExpenseListActivity activity = returnExpenseListActivity(ex1, ex2, ex3);
    	Controller expenseController = activity.getController();
    	ArrayList<Expense> expenseList = ArrayList<Expense>(expenseController.getClaimList());
    	
    	//Sorting will require implementing Comparable within the Claim class.
    	assertEquals("position = 0, expense should be ex2", expenseList.get(0), ex2);
    	assertEquals("position = 1, expense should be ex1", expenseList.get(1), ex1);
    	assertEquals("position = 2, expense should be ex3", expenseList.get(2), ex3);
    }
    
    public void testEmptyList() 
    {
    	ExpenseListActivity activity = returnEmptyExpenseListActivity();
    	Controller expenseController = activity.getController();
    	ArrayList<Expense> expenseList = ArrayList<Expense>(expenseController.getClaimList());
    	
    	assertTrue(expenseList.isEmpty());
    }
    
    public void testClaimListView() 
    {
    	ExpenseListActivity activity = returnExpenseListActivity(ex1, ex2, ex3);
    	
    	//onItemClickListener required for ListView inside ExpenseListActivity.
    	assertEquals("position = 0, expense should be claim2", activity.onItemClick(0), ex2);
    	assertEquals("position = 1, expense should be claim1", activity.onItemClick(1), ex1);
    	assertEquals("position = 2, expense should be claim3", activity.onItemClick(2), ex3);
    	
    }
    
    
    private ExpenseListActivity returnExpenseListActivity(Expense ex1, Expense ex2, Expense ex3)
    {
    	Intent intent = new Intent();
    	//Claim class must implement Serializable, use Claim claim# = (Claim) getIntent().getSerializableExtra("claim#"); to receive within Activity.
    	intent.putExtra(ClaimListActivity.EXPENSE_1, ex1);
    	intent.putExtra(ClaimListActivity.EXPENSE_2, ex2);
    	intent.putExtra(ClaimListActivity.EXPENSE_3, ex3);
    	setActivityIntent(intent);
    	return (ExpenseListActivity) getActivity();
    } 
    
    private ExpenseListActivity returnEmptyExpenseListActivity()
    {
	    Intent intent = new Intent();
	    setActivityIntent(intent);
	    return (ExpenseListActivity) getActivity();
    }
    
}
