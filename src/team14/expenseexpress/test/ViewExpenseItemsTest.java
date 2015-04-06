package team14.expenseexpress.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import team14.expenseexpress.R;
import team14.expenseexpress.activity.ExpenseDetailsActivity;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.Receipt;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class ViewExpenseItemsTest extends
		ActivityInstrumentationTestCase2<ExpenseDetailsActivity> {
	
	ExpenseDetailsActivity activity;
	Instrumentation instrumentation;

	public ViewExpenseItemsTest() {
		super(ExpenseDetailsActivity.class);
	}
	
	@Override
	public void setUp() throws Exception{
		//just setting up the things for tests
		super.setUp();
		this.activity = getActivity();
		this.instrumentation = getInstrumentation();
	}
	
	public void testViewExpenseDetails() {
		
		GregorianCalendar date = new GregorianCalendar(15, 3, 16);
		double amount = 3.00;
		Receipt receipt = new Receipt();
		
		Claim claim = new Claim();
		ClaimController.getInstance().setSelectedClaim(claim);
		ExpenseController.initialize();
		ExpenseController.getInstance().setSelectedExpense(null);
		ExpenseController.getInstance().setExpense("Food", date, amount, "CAD", "I like food", "poutine", false);
		ArrayList<Expense> expenselist = ExpenseController.getInstance().getExpenseList().getExpenses();
		
		TextView expenseNameTitle = (TextView) activity.findViewById(R.id.expenseDetailsTitle);
		TextView expenseCategory = (TextView) activity.findViewById(R.id.expenseDetailsCategory);
		TextView expenseDate = (TextView) activity.findViewById(R.id.expenseDetailsDate);
		TextView expenseAmount = (TextView) activity. findViewById(R.id.expenseDetailsAmount);
		TextView expenseCurrency = (TextView) activity. findViewById(R.id.expenseDetailsCurrency);
		TextView expenseDescription = (TextView) activity.findViewById(R.id.expenseDetailsDescription);
		TextView expenseComplete = (TextView) activity.findViewById(R.id.expenseDetailsINC);
		
		expenseNameTitle.setText(expenselist.get(0).getName());
		expenseCategory.setText(expenselist.get(0).getCategory());
		expenseDate.setText(android.text.format.DateFormat.format("yyyy-MM-dd",expenselist.get(0).getExpenseDate()));
		expenseAmount.setText(Double.toString(expenselist.get(0).getAmount().getNumber()));
		expenseCurrency.setText(expenselist.get(0).getAmount().getCurrency().getName());
		expenseDescription.setText(expenselist.get(0).getDescription());
		expenseComplete.setText(Boolean.toString(expenselist.get(0).getIncomplete()));
		
		assertEquals("expense name textview does not work", "poutine", expenseNameTitle.getText());
		assertEquals("expense date textview does not work", "2015-03-16", expenseDate.getText()); 
		assertEquals("expense amount textview does not work", "3.00", expenseAmount.getText()); 
		assertEquals("expense category textview does not work", "Food", expenseCategory.getText()); 
		assertEquals("expense currency textview does not work", "CAD", expenseCurrency.getText()); 
		assertEquals("expense description textview does not work", "I like food", expenseDescription.getText()); 
		assertEquals("expense complete textview does not work", "false", expenseComplete.getText()); 

	}
}
