package team14.expenseexpress.receipt;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.Mode;
import team14.expenseexpress.controller.ReceiptController;
import team14.expenseexpress.util.ElasticSearchHelper;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ViewReceiptActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_receipt);
		TextView receiptTitle = (TextView) findViewById(R.id.receiptTitle);
		String expenseName = ExpenseController.getInstance().getSelectedExpense().getName();
		receiptTitle.setText(expenseName+ "'s Receipt");
		ImageButton imageButton = (ImageButton) findViewById(R.id.TakeAPhoto);
		imageButton.setClickable(false);
		Button submitButton = (Button) findViewById(R.id.receiptSubmitButton);
		submitButton.setVisibility(View.INVISIBLE);
		imageButton.refreshDrawableState();
		try{
			Bitmap bitmap = ExpenseController.getInstance().getSelectedExpense().getReceipt().StringToBitMap();
			Matrix matrix = new Matrix();
			matrix.postRotate(90);
			Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			Drawable d = new BitmapDrawable(getResources(),newBitmap);
			imageButton.setImageDrawable(null);
			imageButton.setBackground(d);
			
		}
		catch (Exception e){
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_receipt, menu);
		return true;
	}

}