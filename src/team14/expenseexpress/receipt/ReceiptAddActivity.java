package team14.expenseexpress.receipt;

import java.io.File;

import team14.expenseexpress.R;
import team14.expenseexpress.R.layout;
import team14.expenseexpress.R.menu;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.ReceiptController;
import team14.expenseexpress.model.Receipt;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ReceiptAddActivity extends Activity {
	
	private Uri receiptUri = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_receipt);
		ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
		button.refreshDrawableState();
		
		try{
			receiptUri = Uri.parse(ExpenseController.getInstance().getSelectedExpense().getReceipt().getUri());
			Drawable drawable = Drawable.createFromPath(receiptUri.getPath());
			button.setImageDrawable(drawable);
		}
		catch (Exception e){
			
		}
		OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				takeAPhoto();
			}
		};
		button.setOnClickListener(listener);
	}

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	public void takeAPhoto() {
	// TODO: Create an intent with the action
	// MediaStore.ACTION_IMAGE_CAPTURE
	
	
	ComponentName cn = new ComponentName("team14.expenseexpress.receipt.bogopicgen",
						"team14.expenseexpress.receipt.BogoPicGenActivity");


	// Create a folder to store pictures
	String folder = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/tmp";
	File folderF = new File(folder);
	if (!folderF.exists()) {
		folderF.mkdir();
	}

	// Create an URI for the picture file
	String imageFilePath = folder + "/"
			+ String.valueOf(System.currentTimeMillis()) + ".jpg";
	File imageFile = new File(imageFilePath);
	Receipt receipt = ReceiptController.getInstance().getSelectedReceipt();
	receiptUri = Uri.fromFile(imageFile);
	receipt.setUri(Uri.encode(receiptUri.toString()));
	// TODO: Put in the intent in the tag MediaStore.EXTRA_OUTPUT the URI
	Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	intent.putExtra(MediaStore.EXTRA_OUTPUT,receipt.getUri());

	startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	
}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
			if (resultCode == RESULT_OK){
;
				ImageButton ib = (ImageButton) findViewById(R.id.TakeAPhoto);
				Drawable drawable = Drawable.createFromPath(receiptUri.getPath());
				ib.setImageDrawable(drawable);
				
	
			}
		}
	}
		
	public void OnClick_SubmitReceipt(View v){
		ExpenseController.getInstance().getSelectedExpense().setReceipt(ReceiptController.getInstance().getSelectedReceipt());
		Toast.makeText(this, "adding " + ExpenseController.getInstance().getSelectedExpense().getReceipt().getUri().toString(), Toast.LENGTH_LONG).show();
		finish();
	}

}
