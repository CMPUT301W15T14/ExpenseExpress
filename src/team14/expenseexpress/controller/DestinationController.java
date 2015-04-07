package team14.expenseexpress.controller;

import team14.expenseexpress.model.Destination;
import team14.expenseexpress.model.Expense;

public class DestinationController {
	
	private static DestinationController instance = null;
	private Destination selectedDestination = null;
	
	private DestinationController(){
		if(selectedDestination == null) {
			selectedDestination = new Destination();
		}
	}
	
	public static DestinationController getInstance(){
		if (instance == null){
			instance = new DestinationController();
		}
		return instance;
	}
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
