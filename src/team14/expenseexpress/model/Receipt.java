package team14.expenseexpress.model;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

public class Receipt {
	String image;
    
    /**
     * Constructor
     *	
     * 
     */
    public Receipt(){
    	
    }

    public String getImage(){
    	return this.image;
    }

	public void BitmapToString(Bitmap bitmap){
	     ByteArrayOutputStream baos=new  ByteArrayOutputStream();
	     bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
	     byte [] b=baos.toByteArray();
	     this.image = Base64.encodeToString(b, Base64.DEFAULT);
	}


	/**
	 * @param encodedString
	 * @return bitmap (from given string)
	 */
	public Bitmap StringToBitMap(){
		try {
			byte [] encodeByte = Base64.decode(this.image,Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
			return bitmap;
		} catch(Exception e) {
			e.getMessage();
			return null;
		}
}
}	