package team14.expenseexpress.activity;

import team14.expenseexpress.R;
import team14.expenseexpress.controller.ClaimController;
import team14.expenseexpress.model.Destination;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
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
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;

public class EditDestinations extends Activity {
	

	private ListView destinationListView;
	private ArrayAdapter<Destination> destinationAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_destinations);
		
		destinationListView = (ListView) findViewById(R.id.editTagList);
		destinationAdapter = new ArrayAdapter<Destination>(this, R.layout.listtextview, ClaimController.getInstance().getSelectedClaim().getDestinations());
		destinationListView.setAdapter(destinationAdapter);
		registerForContextMenu(destinationListView);
	}

	@SuppressLint("ValidFragment")
	private class NewDestinationDialogFragment extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Dialog dialog = super.onCreateDialog(savedInstanceState);

			// request a window without the title
			dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			return dialog;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			final View v = inflater.inflate(R.layout.activity_add_destinations, container, false);
			final EditText nameText = (EditText) v.findViewById(R.id.addDestinationTextField);
			final EditText reasonText = (EditText) v.findViewById(R.id.addReasonTextField);
			v.findViewById(R.id.addDestinationButton2).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							String name = nameText.getText().toString();
							String reason = reasonText.getText().toString();
							if (name.length() == 0 || reason.length() == 0) {
								return;
							} else {
								Destination destination = new Destination(name);
								destination.setReason(reason);
								destination.setDestination(name);
								// if all good, add to list and dismiss dialog
								ClaimController.getInstance().getSelectedClaim().addDestination(destination);
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

		final ListView lv1 = (ListView) findViewById(R.id.editTagList);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(
				R.array.LongClickMenu);
		String menuItemName = menuItems[menuItemIndex];

		Destination destination = (Destination) lv1.getItemAtPosition(info.position);

		if (menuItemName.equals("Delete")) {
			ClaimController.getInstance().getSelectedClaim().getDestinations().remove(destination);
			destinationAdapter.notifyDataSetChanged();
		}
		return true;
	}
	
	public void addNewDestinations(View v) {
		FragmentManager fm = getFragmentManager();
		new NewDestinationDialogFragment().show(fm, "DestinationsDialogFragment");
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	public void acceptDestinations(View v) {
		finish();
	}
	
}
