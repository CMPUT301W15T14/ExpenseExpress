package team14.expenseexpress.controller;

import android.net.Uri;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;
import team14.expenseexpress.model.Receipt;

public class ReceiptController {
	
	private Receipt selectedReceipt;
	//singleton
	private static ReceiptController instance = null;
	
	private ReceiptController(){
		if(selectedReceipt == null) {
			selectedReceipt = new Receipt();
		}
	}
	
	public static ReceiptController getInstance(){
		if (instance == null){
			instance = new ReceiptController();
		}
		return instance;
	}
	
	public Receipt getSelectedReceipt(){
		return this.selectedReceipt;
	}
	public Receipt setSelectedReceipt(Receipt receipt){
		return this.selectedReceipt = receipt;
	}
	
}
