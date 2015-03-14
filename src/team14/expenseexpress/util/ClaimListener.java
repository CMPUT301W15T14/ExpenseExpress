package team14.expenseexpress.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import team14.expenseexpress.model.Claim;

public class ClaimListener implements PropertyChangeListener {

	public ClaimListener(Claim claim) {
		claim.addListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
