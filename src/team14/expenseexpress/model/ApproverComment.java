package team14.expenseexpress.model;

import java.util.GregorianCalendar;

/**
 * @author   Team 14
 * @version  0.1
 * @since    2015-02-19
 */
public class ApproverComment implements Comparable<ApproverComment> {
	private String approvername;
    private String comment;
    private GregorianCalendar timestamp;


    /**
     * Basic constructor with a time stamp.
     *
     * @param id    The Approver's unique ID, managed by the web server.
     */
    public ApproverComment(){
        timestamp = new GregorianCalendar();
    }
    
    public void setApproverName(String name) {
    	this.approvername = name;
    }


    /**
	 * Getter for the comment.
	 * @return   The comment as a String.
	 */
    public String getComment() {
        return comment;
    }

    /**
	 * Setter for the comment String, with a time stamp. Use this for editing the comment. For an update or follow-up comment, instantiate another ApproverComment.
	 * @param comment    New comment to replace the old one.
	 */
    public void setComment(String comment) {
        this.comment = comment;
        timestamp = new GregorianCalendar();
    }

    /**
	 * Getter for the timestamp.
	 * @return   The timestamp as a GregorianCalendar object.
	 */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Method override to allow ApproverComments to be sorted by timestamp.
     *
     * @param another   The object being compared to for sorting purposes.
     * @return          The result of the comparison between two ApproverComments.
     */
    @Override
    public int compareTo(ApproverComment another) {
        return timestamp.compareTo(another.getTimestamp());
    }
}