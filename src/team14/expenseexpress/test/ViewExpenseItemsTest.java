package team14.expenseexpress.test;

import java.util.GregorianCalendar;

import team14.expenseexpress.activity.ExpenseDetailsActivity;
import team14.expenseexpress.model.Amount;
import team14.expenseexpress.model.Currency;
import team14.expenseexpress.model.Expense;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.TextView;
/**
 * Test to check if we can see a expense's details
 * Related Use Case: UC14
 *
 */
public class ViewExpenseItemsTest extends
		ActivityInstrumentationTestCase2<ExpenseDetailsActivity> {
	
	Instrumentation instrumentation;

	public ViewExpenseItemsTest() {
		super(ExpenseDetailsActivity.class);
		this.instrumentation = getInstrumentation();
	}
	
	@UiThreadTest
	public void testViewExpenseDetails() {
		
		GregorianCalendar date = new GregorianCalendar(15, 3, 16);
		double amount = 3.00;
		Amount actualAmount = new Amount(amount, Currency.fromString(""));
		
		Expense expense = new Expense();
		expense.setAmount(actualAmount);
		expense.setCategory("Food");
		expense.setDescription("I like food");
		expense.setExpenseDate(date);
		expense.setIncomplete(false);
		expense.setName("poutine");
		
		final TextView expenseNameTitle = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.expenseDetailsTitle);
		final TextView expenseCategory = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.expenseDetailsCategory);
		final TextView expenseDate = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.expenseDetailsDate);
		final TextView expenseAmount = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.expenseDetailsAmount);
		final TextView expenseCurrency = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.expenseDetailsCurrency);
		final TextView expenseDescription = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.expenseDetailsDescription);
		final TextView expenseComplete = (TextView) getActivity().findViewById(team14.expenseexpress.R.id.expenseDetailsINC);
		
		expenseNameTitle.setText(expense.getName());
		expenseCategory.setText(expense.getCategory());
		expenseDate.setText(android.text.format.DateFormat.format("yyyy-MM-dd",expense.getExpenseDate()));
		expenseAmount.setText(Double.toString(expense.getAmount().getNumber()));
		expenseCurrency.setText(expense.getAmount().getCurrency().getName());
		expenseDescription.setText(expense.getDescription());
		expenseComplete.setText(Boolean.toString(expense.getIncomplete()));
		
		assertEquals("expense name textview does not work", "poutine", expenseNameTitle.getText());
		assertEquals("expense date textview does not work", "2015-03-16", expenseDate.getText()); 
		assertEquals("expense amount textview does not work", "3.00", expenseAmount.getText()); 
		assertEquals("expense category textview does not work", "Food", expenseCategory.getText()); 
		assertEquals("expense currency textview does not work", "CAD", expenseCurrency.getText()); 
		assertEquals("expense description textview does not work", "I like food", expenseDescription.getText()); 
		assertEquals("expense complete textview does not work", "false", expenseComplete.getText());

	}
}
