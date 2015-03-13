package team14.expenseexpress.model;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class ClaimTag{
    private String name;
    private static final String DEFAULT_NAME = "derp";

    
    public boolean equals(Object o){
    	return o instanceof ClaimTag && name.equals(((ClaimTag) o).getName());
    }
    
    public int hashCode(){
    	return name.hashCode();
    }
    
    /**
     * Empty private constructor.
     */
    private ClaimTag(){
        // empty
    }

    /**
     * Constructor that initializes the tag name, giving a default if empty.
     *
     * @param name  The tag name as a String.
     */
    public ClaimTag(String name){

        if (name == ""){
            this.name = DEFAULT_NAME;
        }
        // TODO: impose other restrictions here
        this.name = name;
    }

    /**
     * Getter for the tag name.
     *
     * @return  The tag name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the tag name.
     *
     * Allowing the tag name to be set means you can change a tag name without affecting
     * the Claims it's attached to.
     *
     * @param name  The new tag name String to replace the old one.
     */
    public void setName(String name) {
        this.name = name;
    }
}