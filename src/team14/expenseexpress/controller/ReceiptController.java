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
	/**
	 * If there is no current instance, returns empty Receipt instance
	 * @return The current instance
	 */
	public static ReceiptController getInstance(){
		if (instance == null){
			instance = new ReceiptController();
		}
		return instance;
	}
	/**
	 * Returns selected receipt
	 * @return instance of selected receipt
	 */
	public Receipt getSelectedReceipt(){
		return this.selectedReceipt;
	}
	/**
	 * 
	 * @param receipt to become selected receipt
	 * @return instance of given receipt
	 */
	public Receipt setSelectedReceipt(Receipt receipt){
		return this.selectedReceipt = receipt;
	}
	/**
	 * Stores picture of receipt
	 * @param receipt to be stored????
	 * @param context
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Bitmap getBitmap(Receipt receipt, Context context) throws FileNotFoundException, IOException{
		Uri uri = receipt.getUri();
		return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
	}
}
