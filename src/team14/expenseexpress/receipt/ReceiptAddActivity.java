package team14.expenseexpress.receipt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.ReceiptController;
import team14.expenseexpress.model.Receipt;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Config;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class ReceiptAddActivity extends Activity {
	
	private Bitmap bitmap;
	private Uri uri;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_receipt);
		
		ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
		button.refreshDrawableState();
		bitmap = null;
		
		try{
			uri = ExpenseController.getInstance().getSelectedExpense().getReceipt().getUri();
			Drawable drawable = Drawable.createFromPath(uri.getPath());
			button.setImageDrawable(drawable);
		}
		catch (Exception e){
			
		}
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				takeAPhoto();
			}
		};
		button.setOnClickListener(listener);
	}

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	/**
	 * Takes a picture using the phone's camera and saves it
	 */
	public void takeAPhoto() {
	// TODO: Create an intent with the action
	// MediaStore.ACTION_IMAGE_CAPTURE
	

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
	uri = Uri.fromFile(imageFile);
	Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
	// TODO: Put in the intent in the tag MediaStore.EXTRA_OUTPUT the URI
	Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
	startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	
}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
			if (resultCode == RESULT_OK){
		        try {
					bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
					if (bitmap != null){
						
				    	while (bitmap.getByteCount() > 65536){
							   bitmap = Bitmap.createScaledBitmap(bitmap,(int) (bitmap.getWidth()*0.2),(int) (bitmap.getHeight()*0.2), true);
						    }


				    
				    File imageFile = new File(uri.getPath());
				    FileOutputStream out = new FileOutputStream(imageFile);
				    bitmap.compress(Bitmap.CompressFormat.JPEG,100, out);
				    out.flush();
				    out.close();
				    
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				ImageView ib = (ImageButton) findViewById(R.id.TakeAPhoto);
				Drawable drawable = Drawable.createFromPath(uri.getPath());
				ib.setImageDrawable(drawable);
			}
		}
	}
		/**
		 * Submits receipt and returns user to previous screen
		 * @param v of type View
		 */
	public void OnClick_SubmitReceipt(View v){
		
		ReceiptController.getInstance().getSelectedReceipt().setUri(uri);
		ExpenseController.getInstance().getSelectedExpense().setReceipt(ReceiptController.getInstance().getSelectedReceipt());
		
		finish();
	}

}
