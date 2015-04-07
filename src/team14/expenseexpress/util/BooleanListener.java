package team14.expenseexpress.util;

//stackoverflow.com/questions/9879780/android-how-to-make-listener-to-a-custom-variable       Accessed:  April.7th, 2015

public class BooleanListener {
	private boolean booleanCheck = false;
	// all the listener stuff below
    public interface Listener {
        public void onStateChange(boolean state);
    }

    private Listener boolListener = null;
    public void registerListener (Listener listener) {
        boolListener = listener;
    }

    public void doYourWork() {
        // do things here
        // at some point
        booleanCheck = true;
        // now notify if someone is interested.
        if (boolListener != null)
            boolListener.onStateChange(booleanCheck);
    }
}