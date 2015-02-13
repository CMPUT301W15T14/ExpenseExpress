import android.test.ActivityInstrumentationTestCase2;


public class EditExpenseTest extends 
		ActivityInstrumentationTestCase2<ExpenseReceiptActivity> {
	
	public ImageView view = (ImageView) findViewById(R.id.receiptView);
	public Expense expense;
	
	public EditExpenseTest() {
		super(ExpenseReceiptActivity.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void ChangeExpenseReceiptTest(){
		expense = new Expense();	
		
		//Test to check if a receipt can be deleted
		Drawable oldphoto = view.getDrawable();
		expense.addReceipt(oldphoto);
		expense.deleteReceipt();
		assertNull("not deleted", expense.getReceipt);
		
		//Test to check if we can add another receipt to replace the one deleted
		takePhoto(); //or choosePhoto();
		Drawable newphoto = view.getDrawable();
		expense.addReceipt(newphoto);
		assertNotNull("no picture", expense.getReceipt());
	}

}
