package team14.expenseexpress;


import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.DestinationController;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.UserController;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends Activity implements LocationListener {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		 Bundle extras = getIntent().getExtras();
		  if (extras != null) {
		   if (extras.get("ID") == "USER"){
			   user = true;
			   expense =false;
		   }
		   else{
			   expense = true;
			   user = false;
		   }
		  }else{
			  user = false;
			  expense = false;
		  }
	}

	public TextView latView;
	public TextView lngView;
	public boolean user;
	public boolean expense;
	    // flag for GPS status
	    boolean isGPSEnabled = false;
	    Location location; // location
	    double latitude;
	    double longitude;
	    // flag for network status
	    boolean isNetworkEnabled = false;
	 
	    // flag for GPS status
	    boolean canGetLocation = false;
	    protected LocationManager locationManager;
	    // The minimum distance to change Updates in meters
	    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	 
	    // The minimum time between updates in milliseconds
	    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	    
    public void getLocation() {
        try {
            locationManager = (LocationManager) this
                    .getSystemService(LOCATION_SERVICE);
 
            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
 
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
 
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
    public void getCoordinates(View v){
    	getLocation();
    	TextView latView = (TextView) findViewById(R.id.GPSlat);
    	TextView lngView = (TextView) findViewById(R.id.GPSLong);
    	latView.setText("Latitude: ");
    	lngView.setText("Longitude: ");
    	latView.setText(latView.getText() + String.valueOf(latitude));
    	lngView.setText(lngView.getText() + String.valueOf(longitude));
    	if (user){
    		UserController.getInstance().setLatitude(latitude);
    		UserController.getInstance().setLongitude(longitude);
    	}
    	else if (expense){
    		ExpenseController.getInstance().getSelectedExpense().setLatitude(latitude);
    		ExpenseController.getInstance().getSelectedExpense().setLongitude(longitude);

    	}
    	else{
    
    	DestinationController.getInstance().setDestLatitude(latitude);
    	DestinationController.getInstance().setDestLongitude(longitude);}
    	
    	
    }
    public void acceptCoordinates(View v){
    	finish();
    }
    
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
