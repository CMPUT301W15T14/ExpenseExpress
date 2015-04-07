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
	private double latitude;
	private double longitude;
    //private Mode userMode; TODO pp5 after server is setup to save user info.
    /**
     * Constructor that initializes the User with a usernameString.
     *
     * @param String name  The name of user
     */
    public User(String name){
    	this.name = name;
    }
    /**
     * Constructor that initializes the User with a usernameString.
     *	With a password
     * @param String name, String  password  The name of user & password
     */
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    /**
     * getter that retrieves the User name .
     *	
     * @return String name
     */
	public String getName() {
		return name;
	}
    /**
     * getter that retrieves the Resource_URL .
     *	
     * @return RESOURCE_URL
     */
	public String getResourceUrl() {
		return RESOURCE_URL;
	}
    /**
     * Override method that checks whether an object
     *	is and instance of User 
     * @return boolean instanceof
     */
	@Override
	public boolean equals(Object object){
		if(!(object instanceof User)) {
        	throw new ClassCastException();
        }
		User u = (User)object;
		return this.getName().equals(u.getName());
		
	}
    /** Override
     * method hat gives a hashcode of the username .
     *	
     * @return name.hashcode():
     */
	@Override
	public int hashCode(){
		return name.hashCode();
	}
    /**
     * getter that retrieves the password .
     *	
     * @return password
     */
	public String getPassword() {
		return password;
	}
    /**
     * setter that sets the password .
     *	
     * @param String password
     */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void getUserMode() {
		//TODO pp5 after server is setup to save user info.
	}
	
	public void setUserMode(Mode mode) {
		//TODO pp5 after server is setup to save user info.
	}
	/**
	 * Gets current Latitude
	 * @return latitude of type Double
	 */
    public double getLatitude() {
		return latitude;
	}
    /**
     * sets the current latitude
     * @param latitude 
     */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * Gets current Longitude
	 * @return longitude of type Double
	 */
	public double getLongitude() {
		return longitude;
	}
    /**
     * sets the current longitude
     * @param longitude 
     */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
     * enum that retrieves the mode.
     *	
     * @return password
     */
	private enum Mode {
		CLAIMANT,
		APPROVER,
		CLAIMANT_APPROVER,
		OFFLINE,
		//TODO implement mode conditions...
	}	
}