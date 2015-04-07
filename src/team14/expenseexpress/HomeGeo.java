package team14.expenseexpress;

import team14.expenseexpress.activity.ClaimListActivity;
import team14.expenseexpress.controller.UserController;
import team14.expenseexpress.maps.MapActivity;
import team14.expenseexpress.util.LocalFileHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HomeGeo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_geo);
		TextView latView = (TextView) findViewById(R.id.homeLat);
		TextView lngView = (TextView) findViewById(R.id.homeLong);
		try{
			latView.setText("Latitude : " + String.valueOf(UserController.getInstance().getLatitude()));
			lngView.setText("Longitude : " + String.valueOf(UserController.getInstance().getLongitude()));
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
	}
	public void onClick_ByGPS(View v){
		Intent intent = new Intent(HomeGeo.this,
				LocationActivity.class);
		intent.putExtra("ID", "USER");
		startActivity(intent);
	}
	@Override
	public void onResume(){
		super.onResume();
		try{
			TextView latView = (TextView) findViewById(R.id.homeLat);
			TextView lngView = (TextView) findViewById(R.id.homeLong);
			latView.setText("Latitude : " + String.valueOf(UserController.getInstance().getLatitude()));
			lngView.setText("Longitude: " + String.valueOf(UserController.getInstance().getLongitude()));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void onClick_ByMap(View v){
		 Intent intent = new Intent(HomeGeo.this, MapActivity.class);
		 intent.putExtra("ID", "USER");
         startActivity(intent);
	}
	public void onClick_Submit(View v){
		SharedPreferences prefs = getSharedPreferences("home", 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putFloat("HomeLat", (float) UserController.getInstance().getLatitude());
		editor.putFloat("HomeLong", (float) UserController.getInstance().getLongitude());
		editor.apply();
		finish();
	}
	
}
