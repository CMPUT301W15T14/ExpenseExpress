package team14.expenseexpress.util;


//stackoverflow.com/questions/9879780/android-how-to-make-listener-to-a-custom-variable       Accessed:  April.7th, 2015

public class BooleanListener {
	private boolean boolCheck = false;
	private Listener myListener = null;
	
	
	public interface Listener{
        public void onStateChange(boolean state);
    }
	
	public void setBooleanListener(Listener listener){
        myListener = listener;
    }

    public void execute(){
    	boolCheck = true;
        if(myListener != null){
            myListener.onStateChange(boolCheck);
        }
    }
	
}
