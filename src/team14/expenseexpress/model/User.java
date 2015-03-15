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
	static final String INSTANCE_NOT_SET = "No instance user defined";
    public final String name;
    private String password;
    
    // Static instance field added (it won't be serialized by Gson)--------------------
    private static User instance;
    public static void setInstance(User user){
    	instance = user;
    }
    public static User getInstance(){
    	if (instance == null){
    		throw new RuntimeException(INSTANCE_NOT_SET);
    	}
    	
    	return instance;
    }
    // ---------------------------------------------------------------------------------

    public User(String name){
    	this.name = name;
    }
    
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

	public String getName() {
		// TODO Auto-generated method stub
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

}