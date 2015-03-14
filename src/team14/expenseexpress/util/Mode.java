package team14.expenseexpress.util;

/**
 * Singleton-like entity for holding claimant vs. approver information.
 * 
 * @author hzou
 */
public enum Mode {;
	public static final int UNDEFINED = 0;
	public static final int CLAIMANT = 1;
	public static final int APPROVER = 2;
	
	private static int mode;
	
	public static void set(int mode){
		if (mode == CLAIMANT || mode == APPROVER){
			Mode.mode = mode;
		}
	}
	public static int get(){
		return mode;
	}
}