import java.io.ByteArrayOutputStream;

import team14.teamproject.test.R;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import junit.framework.TestCase;


public class ExpenseReceiptTest extends ActivityInstrumentationTestCase2<ExpenseReceiptActivity> {
	ImageView view = (ImageView) findViewById(R.id.receiptView);
	public ExpenseReceiptTest() {
		super(ExpenseReceiptActivity.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void AddExpenseReceiptTest(){
		takePhoto(); // or choosePhoto();
		Drawable photo = view.getDrawable();
		Bitmap bitmap = ((BitmapDrawable)photo).getBitmap();
		ByteArrayOutputStream picture = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, picture);
		byte[] picturedata = picture.toByteArray();
		assertTrue("the size of the imagine is oversized", picturedata.length<65536);
	}
	public void ZoomIn(){
		
	}
	public void ZoomOut(){
		
	}
	public void ChangeExpenseReceiptTest(){
	
		Drawable oldphoto = view.getDrawable();
		takePhoto(); //or choosePhoto();
		Drawable newphoto = view.getDrawable();
		assertNotSame("photo did not change", newphoto, oldphoto);
	}

	public void DeleteExpenseReceiptTest(){
	
		deletePhoto();
		assertEquals("Testing Delete", null, view.getDrawable());
	}
	
}
