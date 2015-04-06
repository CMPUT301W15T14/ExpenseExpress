package team14.expenseexpress.controller;



import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import team14.expenseexpress.model.Receipt;

public class ReceiptController {
	
	private Receipt selectedReceipt;
	//singleton
	private static ReceiptController instance = null;
	
	private ReceiptController(){
		if(selectedReceipt == null) {
			selectedReceipt = new Receipt();
		}
	}
	
	public static ReceiptController getInstance(){
		if (instance == null){
			instance = new ReceiptController();
		}
		return instance;
	}
	
	public Receipt getSelectedReceipt(){
		return this.selectedReceipt;
	}
	public Receipt setSelectedReceipt(Receipt receipt){
		return this.selectedReceipt = receipt;
	}
	
	public Bitmap getBitmap(Receipt receipt, Context context) throws FileNotFoundException, IOException{
		Uri uri = receipt.getUri();
		return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
	}
}
