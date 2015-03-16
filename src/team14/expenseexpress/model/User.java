package team14.expenseexpress.model;

/**
 * @author  Team 14
 * @version 0.2
 * @since   2015-03-10
 */
public class User {
	/*
	 *  Only 1 field (String) required for ID since we aren't implementing User rename feature.
	 *  
	 *  However, holding this String in a User class instead of simply passing Strings makes it
	 *  easier in the future to implement something like a rename feature - just add field(s) and
	 *  update equals() and hashcode() 
	 *  
	 */
	
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t14/users/";
    
	private final String name;
    private String password = null;
    //private Mode userMode; TODO pp5 after server is setup to save user info.

    public User(String name){
    	this.name = name;
    }
    
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

	public String getName() {
		return name;
	}
	
	public String getResourceUrl() {
		return RESOURCE_URL;
	}
	
	@Override
	public boolean equals(Object o){
		return o instanceof User && name.equals(((User) o).getName());
	}
	
	@Override
	public int hashCode(){
		return name.hashCode();
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void getUserMode() {
		//TODO pp5 after server is setup to save user info.
	}
	
	public void setUserMode(Mode mode) {
		//TODO pp5 after server is setup to save user info.
	}
	
	private enum Mode {
		CLAIMANT,
		APPROVER,
		CLAIMANT_APPROVER,
		OFFLINE,
		//TODO implement mode conditions...
	}	
}