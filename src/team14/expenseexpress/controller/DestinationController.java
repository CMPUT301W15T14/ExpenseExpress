package team14.expenseexpress.controller;

import team14.expenseexpress.model.Destination;
import team14.expenseexpress.model.Expense;

/**
 * Singleton controller class for managing geo-coordinate and destination data models.
 * @author  Team 14
 * @date  April 6, 2015
 * @version  1.5
 */
public class DestinationController {
	
	// singleton
	/**
	 */
	private static DestinationController instance = null;
	/**
	 */
	private Destination selectedDestination = null;
	
	private DestinationController(){
		if(selectedDestination == null) {
			selectedDestination = new Destination();
		}
	}
	
	/**
	 * @return
	 */
	public static DestinationController getInstance(){
		if (instance == null){
			instance = new DestinationController();
		}
		return instance;
	}
	/**
	 * @return
	 */
	public Destination getSelectedDestination(){
		return this.selectedDestination;
	}
	public void makeSelectedDestination()
	{
		selectedDestination = new Destination();
	}
	public void setDestLatitude(double latitude){
		selectedDestination.setLatitude(latitude);
	}
	public double getDestLatitude(){
		return selectedDestination.getLatitude();
	}
	public void setDestLongitude(double longitude){
		selectedDestination.setLongitude(longitude);
	}
	public double getDestLongitude(){
		return selectedDestination.getLongitude();
	}
	
	
	public void setDestName(String name){
		selectedDestination.setDestination(name);
	}
	public String getDestName(){
		return selectedDestination.getDestination();
	}
	public void setDestReason(String reason){
		selectedDestination.setReason(reason);
	}
	public String getDestReason(){
		return selectedDestination.getReason();
	}
	
	
	
	
	
	
	
}
