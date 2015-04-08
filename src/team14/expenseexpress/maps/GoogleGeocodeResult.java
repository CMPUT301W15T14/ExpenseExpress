package team14.expenseexpress.maps;


/**
 * @author  zbudinsk
 */
public class GoogleGeocodeResult {
	/**
	 */
	public Results[] results;
	
	public String getAddress(){
		return results[0].formatted_address;
	}
	
	public String getLatitude(){
		return results[0].geometry.location.lat;
	}
	
	public String getLongitude(){
		return results[0].geometry.location.lng;
	}
}