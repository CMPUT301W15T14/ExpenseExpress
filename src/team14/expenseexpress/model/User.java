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
    public final String name;

    public User(String name){
        this.name = name;
    }

	public String getName() {
		// TODO Auto-generated method stub
		return name;
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