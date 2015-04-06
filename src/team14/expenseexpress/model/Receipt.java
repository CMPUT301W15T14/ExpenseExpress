package team14.expenseexpress.model;

import android.net.Uri;

public class Receipt {
	private Uri	 uri;
    
    /**
     * Constructor
     *	
     * 
     */
    public Receipt(){
    	
    }
    
    public void setUri(Uri uri){

    this.uri = uri;

    }
    public Uri getUri(){
    	return this.uri;
    }

}
