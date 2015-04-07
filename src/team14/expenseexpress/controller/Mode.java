package team14.expenseexpress.controller;

/**
 * Singleton-like entity for holding claimant vs. approver information.
 * 
 * @author hzou
 */
public enum Mode {;
	public static final int OFFLINE = 0;
	public static final int CLAIMANT = 1;
	public static final int APPROVER = 2;
	
	private static int mode;
	/**
	 * passes and receives mode
	 * @param mode the mode that the user wants upon starting app
	 */
	public static void set(int mode){
		if (mode == CLAIMANT || mode == APPROVER || mode == OFFLINE){
			Mode.mode = mode;
		}
	}
	/**
	 * mode is either CLAIMANT, APPROVER or OFFLINE
	 * @return Current mode
	 */
	public static int get(){
		return mode;
	}
}