package team14.expenseexpress.maps;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.DestinationController;
import team14.expenseexpress.controller.ExpenseController;
import team14.expenseexpress.controller.UserController;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Using Google static maps web API, load maps based on coordinates and calculate geo-coordinates from
 * clicked position on said map. Also allows manipulation of map via movement and zoom controls.
 * 
 * @date April 7, 2015
 * @author Team 14
 * @version 1.5
 */
public class MapActivity extends Activity {
	
	private String latitude;
	private String longitude;
	private ImageView map;
	private TextView textView_zoom;
	private TextView textView_coordinatesClicked;
	private final int screenWidth = 400;
	private final int screenHeight = 640; // in pixels. Google provides a maximum length of 640 pixels for free
	private int zoom = 10;
	private final String coordinatesClicked = "";
	private String mapType = "roadmap";
	private final ArrayList<String> markers = new ArrayList<String>();
	private final String API_KEY = "AIzaSyC1xOmx5JlATSw4iaLy_MFVU1FBNq7RqII";
	private final double scrollSpeed = 75;
	private boolean user;
	private boolean expense;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		map = (ImageView) findViewById(R.id.imageView_map);
		textView_zoom = (TextView) findViewById(R.id.textView_zoom);
		textView_coordinatesClicked = (TextView) findViewById(R.id.textView_coordinatesClicked);
		latitude = String.valueOf(UserController.getInstance().getLatitude());
	    longitude = String.valueOf(UserController.getInstance().getLongitude());
	    Bundle extras = getIntent().getExtras();
	    user = false;
	    expense = false;
		  if (extras != null) {
			   if (extras.get("ID").equals("USER")){
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
		
	
		map.setOnTouchListener(new MapOnTouchListener());
	
		// generateTestMarkers();
		
		showMap();
	}
	
	// Not used in final release
	private void generateTestMarkers() {
		markers.add("&markers=color:blue%7Clabel:S%7C0,10");
		markers.add("%7C5,10");
		markers.add("%7C10,10");
		markers.add("%7C15,10");
		markers.add("%7C20,10");
		markers.add("%7C25,10");
		markers.add("%7C30,10");
		markers.add("%7C35,10");
		markers.add("%7C40,10");
		markers.add("%7C45,10");
		markers.add("%7C50,10");
		markers.add("%7C55,10");
		markers.add("%7C60,10");
		markers.add("%7C65,10");
		markers.add("%7C70,10");
		markers.add("%7C75,10");
		markers.add("%7C80,10");
		markers.add("%7C85,10");
		markers.add("%7C90,10");		
	}


	/**
	 * Implementation of OnTouchListener that translates the pixel position into
	 * geo-coordinates.
	 * 
	 * Mathematical approximations are used rather than haversine.
	 * 
	 */
	public class MapOnTouchListener implements View.OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			int pixelX = (int) motionEvent.getX();
			int pixelY = (int) motionEvent.getY();
			int maxX = map.getWidth();
			int maxY = map.getHeight();
			
			double latitudeDouble = Double.valueOf(latitude);
			double latitudeDoubleAbs = Math.abs(latitudeDouble);
			double latitudeFactor = 0.0;
			
			// Explanation of the math: the pixels per degree latitude changes depending on the absolute
			// value of the latitude (due to the Google map image being a mercator projection.
			// This is accounted for (approximately) below with the switch-case.
			// The latitudeFactor below is based on pixel difference in 1 degree latitude at a zoom level of 8.
			// Longitude does not need correction.
			int latitudeTensDigit = (int) (latitudeDoubleAbs+5)/10;
			switch (latitudeTensDigit){
			case 0:
				latitudeFactor = 320;
				break;
			case 1:
				latitudeFactor = 330;
				break;
			case 2:
				latitudeFactor = 360;
				break;
			case 3:
				latitudeFactor = 395;
				break;
			case 4:
				latitudeFactor = 450;
				break;
			case 5:
				latitudeFactor = 550;
				break;
			case 6:
				latitudeFactor = 730;
				break;
			case 7:
				latitudeFactor = 840;
				break;
			case 8:
				latitudeFactor = 1600;
				break;
			}
			
			// The pixel difference ("delta px" in other words) is a function of the zoom level:
			double pixelDifferencePerDegreeLatitude = latitudeFactor/(Math.pow(2,8-zoom));
			double pixelDifferencePerDegreeLongitude = 320.0/(Math.pow(2,8-zoom));

                        // Finally, the paramter MotionEvent's x and y pixel coordinates are converted to geo-coordinates.
			int pixelClickedRelativeToCenterY = (maxY/2)-pixelY;
			double latitudeClicked = Double.valueOf(latitude) + pixelClickedRelativeToCenterY / pixelDifferencePerDegreeLatitude;
			
			int pixelClickedRelativeToCenterX = pixelX-(maxX/2);
			double longitudeClicked = Double.valueOf(longitude) + pixelClickedRelativeToCenterX / pixelDifferencePerDegreeLongitude;
			
			textView_coordinatesClicked.setText("Coordinates clicked: "+ String.format("%1$,.4f", latitudeClicked) + ", " + String.format("%1$,.4f", longitudeClicked));
			// addMarker(latitudeClicked, longitudeClicked);
			DecimalFormat df = new DecimalFormat("#.#####");
			df.format(0.912385);
			
		// The block below sets either the Home geo-coordinates, that of an expense, or one of the ones of a claim.
	    	if (user){
	    		UserController.getInstance().setLatitude(Double.valueOf(df.format(latitudeClicked)));
	    		UserController.getInstance().setLongitude(Double.valueOf(df.format(longitudeClicked)));
	    	}
	    	else if (expense){
	    		ExpenseController.getInstance().getSelectedExpense().setLatitude(Double.valueOf(df.format(latitudeClicked)));
	    		ExpenseController.getInstance().getSelectedExpense().setLongitude(Double.valueOf(df.format(longitudeClicked)));
	    	}
	    	else{
	    
	    		DestinationController.getInstance().setDestLatitude(Double.valueOf(df.format(latitudeClicked)));
	    		DestinationController.getInstance().setDestLongitude(Double.valueOf(df.format(longitudeClicked)));
	    	}
	    	finish();
			return false;
		}

                /**
                 * Adds a marker to the Google static map. Not used in final delivery.
                 */
		private void addMarker(double latitude, double longitude) {
			String label = "J";
			String markerString = "&markers=color:red%7Clabel:"+label+"%7C" + latitude + "," + longitude;
			markers.add(markerString);
			showMap();
		}
	}
	
	/**
	 * Shows the map using Google static maps web API.
	 */ 
	private void showMap(){
		textView_zoom.setText("Zoom: "+zoom);
		new DownloadMapTask().execute(latitude, longitude);
	}
	
	/**
	 * Updates the Google static map with one zoomed in by a factor of 2.
	 */ 
	public void onClick_zoomIn(View v){
		zoom++;
		showMap();
	}
	
	/**
	 *  Updates the Google static map with one zoomed out by a factor of 2.
	 */
	public void onClick_zoomOut(View v){
		if (zoom > 0){
			zoom--;
			showMap();
		}
	}
	
	/**
	 * Toggles between roadmap and satellite views of the google static map.
	 */ 
	public void onClick_toggleView(View v){
		if (mapType.equals("roadmap")){
			mapType = "satellite";
		} else {
			mapType = "roadmap";
		}
		showMap();
	}
	
	/**
	 * Implemention of AsyncTask that downloads a Google static map based on latitude, longitude, zoom and map type.
	 * 
	 * The String... params takes a latitude and longitude as Strings.
	 */ 
	public class DownloadMapTask extends AsyncTask<String, Void, Bitmap>{

		/**
		 * Updates the map ImageView on the UI thread.
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
	        map.setImageBitmap(result);
	    }
		
		/**
		 * Queries the Google static maps API for a specifc map image and returns it for onPostExecute.
		 * 
		 * Manages a background thread for this automatically.
		 * 
		 */
		 
		 // Some of the code used from http://stackoverflow.com/questions/8992964/android-load-from-url-to-bitmap (silentduke's answer), April 6, 2015
		@Override
		protected Bitmap doInBackground(String... coordinates) {
			String latitude = coordinates[0];
			String longitude = coordinates[1];
			String url = "https://maps.googleapis.com/maps/api/staticmap?center="+latitude+","+longitude+"&zoom=" + zoom + "&size=" +
			screenWidth + "x" + screenHeight + "&maptype=" + mapType + "&key=" + API_KEY;
			
			for (int i = 0; i < markers.size(); i++){
				url += markers.get(i);
			}
			Bitmap googleMap = null;
			try {
	            InputStream in = new java.net.URL(url).openStream();
	            googleMap = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return googleMap;
		}
	}
	
	/**
	 * Increases the center point's latitude (moves the map north) and updates with a new map image.
	 * 
	 * The latitude increase is proportional to the zoom level, so a consistent "movement" of the map is achieved
	 * regardless of zoom level.
	 */ 
	public void onClick_up(View v){
		double latitudeDouble = Double.valueOf(latitude);
		latitudeDouble += scrollSpeed/Math.pow(2,zoom);
		latitude = "" + latitudeDouble;
		showMap();
	}
	
	/**
	 * Decreases the center point's latitude (moves the map south) and updates with a new map image.
	 * 
	 * The latitude decrease is proportional to the zoom level, so a consistent "movement" of the map is achieved
	 * regardless of zoom level.
	 */ 
	public void onClick_down(View v){
		double latitudeDouble = Double.valueOf(latitude);
		latitudeDouble -= scrollSpeed/Math.pow(2,zoom);
		latitude = "" + latitudeDouble;
		showMap();
	}
	
	/**
	 * Decreases the center point's longitude (moves the map west) and updates with a new map image.
	 * 
	 * The longitude decrease is proportional to the zoom level, so a consistent "movement" of the map is achieved
	 * regardless of zoom level.
	 */ 
	public void onClick_left(View v){
		double longitudeDouble = Double.valueOf(longitude);
		longitudeDouble -= scrollSpeed/Math.pow(2,zoom);
		longitude = "" + longitudeDouble;
		showMap();
	}
	
	/**
	 * Increases the center point's longitude (moves the map east) and updates with a new map image.
	 * 
	 * The longitude increase is proportional to the zoom level, so a consistent "movement" of the map is achieved
	 * regardless of zoom level.
	 */ 
public void onClick_right(View v){
		
		double longitudeDouble = Double.valueOf(longitude);
		longitudeDouble += scrollSpeed/Math.pow(2,zoom);
		longitude = "" + longitudeDouble;
		showMap();
	}
	
}
