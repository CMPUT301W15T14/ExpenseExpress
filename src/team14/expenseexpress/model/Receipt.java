package team14.expenseexpress.model;



import android.net.Uri;

public class Receipt {
    private Uri uri;
    
    /**
     * Constructor
     *	
     * 
     */
    public Receipt(){
    	
    }
    /**
     * Method to get the uri from a receipt
     *	
     * @return String uri
     */
    public Uri getUri(){
    	return this.uri;
    }
    /**
     * Method to set the uri to a receipt
     *	
     * @param
     */
    public void setUri(Uri uri){
    	this.uri = uri;
    }

}
