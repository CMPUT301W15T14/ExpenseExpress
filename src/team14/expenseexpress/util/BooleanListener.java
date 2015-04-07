package team14.expenseexpress.util;

import team14.expenseexpress.ApproverAdapter;


//stackoverflow.com/questions/9879780/android-how-to-make-listener-to-a-custom-variable       Accessed:  April.7th, 2015

public class BooleanListener {
	private boolean boolCheck = false;
	private Listener listener = null;
	
	
	public interface Listener{
        public void onStateChange(boolean bool);
    }
	
	public void setBooleanListener(BooleanListener listener){
        listener = listener;
    }

    public void execute(){
    	boolCheck = true;
        if(listener != null){
            listener.onStateChange(boolCheck);
        }
    }
	
}