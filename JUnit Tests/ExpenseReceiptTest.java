package team14.expenseexpress.tests;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import team14.teamproject.test.R;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import junit.framework.TestCase;
public class ExpenseReceiptTest extends ActivityInstrumentationTestCase2<ExpenseReceiptActivity> {
public ImageView view = (ImageView) findViewById(R.id.receiptView);
public Expense expense;
public ExpenseReceiptTest() {
super(ExpenseReceiptActivity.class);
}
protected void setUp() throws Exception {
super.setUp();
}
public void AddExpenseReceiptTest(){
//Test to check if the receipt is under 65536 bytes
expense = new Expense();
takePhoto(); // or choosePhoto();
Drawable photo = view.getDrawable();
Bitmap bitmap = ((BitmapDrawable)photo).getBitmap();
ByteArrayOutputStream picture = new ByteArrayOutputStream();
bitmap.compress(Bitmap.CompressFormat.JPEG, 100, picture);
byte[] picturedata = picture.toByteArray();
assertTrue("the size of the imagine is oversized", picturedata.length<65536);
//Test to check if the receipt was added to the expense properly
expense = new Expense();
expense.addReceipt(picture);
assertNotNull("no picture", expense.getReceipt());
}
}