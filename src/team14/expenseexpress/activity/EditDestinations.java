package team14.expenseexpress.activity;

import team14.expenseexpress.LocationActivity;
import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.controller.DestinationController;
import team14.expenseexpress.maps.MapActivity;
import team14.expenseexpress.model.Destination;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;

public class EditDestinations extends Activity{
	private NewDestinationDialogFragment fragment;

	private ListView destinationListView;
	private ArrayAdapter<Destination> destinationAdapter;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_destinations);
		
		destinationListView = (ListView) findViewById(R.id.editDestList);
		destinationAdapter = new ArrayAdapter<Destination>(this, R.layout.listtextview, ClaimController.getInstance().getSelectedClaim().getDestinations());
		destinationListView.setAdapter(destinationAdapter);
		registerForContextMenu(destinationListView);
	}

	@SuppressLint("ValidFragment")
	private class NewDestinationDialogFragment extends DialogFragment {

		View view;
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Dialog dialog = super.onCreateDialog(savedInstanceState);

			// request a window without the title
			dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			return dialog;
		}

		public void update(){
			final TextView latText = (TextView) view.findViewById(R.id.AddDestLatitude);
			final TextView lngText = (TextView) view.findViewById(R.id.addDestLongitude);
	    	latText.setText("Latitude: ");
	    	lngText.setText("Longitude: ");
	    	latText.setText(latText.getText() + String.valueOf(DestinationController.getInstance().getDestLatitude()));
	    	lngText.setText(lngText.getText() + String.valueOf(DestinationController.getInstance().getDestLongitude()));
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			final View v = inflater.inflate(R.layout.activity_add_destinations, container, false);
			view = v;
			final EditText nameText = (EditText) v.findViewById(R.id.addDestinationTextField);
			final EditText reasonText = (EditText) v.findViewById(R.id.addReasonTextField);
			final TextView latText = (TextView) v.findViewById(R.id.AddDestLatitude);
			final TextView lngText = (TextView) v.findViewById(R.id.addDestLongitude);
			latText.setText("Latitude: 0");
			lngText.setText("Longitude: 0");
			DestinationController.getInstance().makeSelectedDestination();
			
			v.findViewById(R.id.addDestGPSButton).setOnClickListener(
					
					new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							 Intent intent = new Intent(getActivity(), LocationActivity.class);
				             startActivity(intent);

					}});
	
			v.findViewById(R.id.addDestMap).setOnClickListener(
					new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							 Intent intent = new Intent(getActivity(), MapActivity.class); 
				             startActivity(intent);

					}});
			v.findViewById(R.id.addDestinationButton2).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							String name = nameText.getText().toString();
							String reason = reasonText.getText().toString();
							if (name.length() == 0 || reason.length() == 0) {
								return;}
							else if( (DestinationController.getInstance().getDestLatitude() == 0.0) 
									&& (DestinationController.getInstance().getDestLongitude() == 0.0)){
								return;
							
							} else {
								
								DestinationController.getInstance().setDestReason(reason);
								DestinationController.getInstance().setDestName(name);
								// if all good, add to list and dismiss dialog
								ClaimController.getInstance().getSelectedClaim().addDestination(DestinationController.getInstance().getSelectedDestination());
								destinationAdapter.notifyDataSetChanged();
								dismiss();
							}
						}
					});
			return v;
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Delete");
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		final ListView lv1 = (ListView) findViewById(R.id.editDestList);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		
		Destination destination = (Destination) lv1.getItemAtPosition(info.position);
		ClaimController.getInstance().getSelectedClaim().getDestinations().remove(destination);
		destinationAdapter.notifyDataSetChanged();
		
		return true;
	}
	/**
	 * Allows user to add new destination through Fragment Manager
	 * @param v View
	 */
	public void addNewDestinations(View v) {
		FragmentManager fm = getFragmentManager();
		fragment = new NewDestinationDialogFragment();
		fragment.show(fm, "DestinationsDialogFragment");
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (fragment == null){
			return;
		} else {
			fragment.update();
		}
	}

	/**
	 * Leaves activity for previous activity
	 * @param v View
	 */
	public void acceptDestinations(View v) {
		finish();
	}


	}
	

