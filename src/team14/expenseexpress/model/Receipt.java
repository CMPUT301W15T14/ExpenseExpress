package team14.expenseexpress.model;

import android.net.Uri;

public class Receipt {
	private String	uri;
    
    /**
     * Constructor
     *	
     * 
     */
    public Receipt(){
    	
    }
    
    public void setUri(Uri uri){
    
    this.uri = uri.toString();

    }
    public Uri getUri(){
    	return Uri.parse(this.uri);
    }

}
