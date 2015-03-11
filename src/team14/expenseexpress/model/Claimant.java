package team14.expenseexpress.model;

import java.util.ArrayList;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public class Claimant extends User {
    public Claimant(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<ClaimTag> tags;

    /**
     * Getter for the list of ClaimTags used by the Claimant.
     *
     * @return  The ClaimTags in an ArrayList.
     */
    public ArrayList<ClaimTag> getTags() {
        return tags;
    }

    /**
     * Setter for the ArrayList holding the ClaimTags associated with the Claimant.
     *
     * @param tags  The ClaimTags in an ArrayList.
     */
    public void setTags(ArrayList<ClaimTag> tags) {
        this.tags = tags;
    }

}