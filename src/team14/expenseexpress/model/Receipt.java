package team14.expenseexpress.model;



import android.net.Uri;

public class Receipt {
    private String uri;
    
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
    public String getUri(){
    	return this.uri;
    }
    /**
     * Method to set the uri to a receipt
     *	
     * @param String uri
     */
    public void setUri(String uri){
    	this.uri = uri;
    }

}
