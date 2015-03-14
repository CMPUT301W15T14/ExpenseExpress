package team14.expenseexpress.model;

import java.util.ArrayList;
import java.util.Collections;

public class ExpenseList {

	private ArrayList<Expense> expenseList = null;
	
	public ExpenseList() {
		this.expenseList = new ArrayList<Expense>();
	}

	public ArrayList<Expense> getExpenses() {
		return this.expenseList;
	}
	
	public void addExpense(Expense newExpense) {
		this.expenseList.add(newExpense);
	}
	
	public void remove(Expense oldExpense) {
		this.expenseList.remove(oldExpense);
	}
	
	public int size() {
		return this.expenseList.size();
	}
	
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(expenseList,new Expense.ExpenseComparator());
	}
	public void clear() {
		expenseList.clear();
	}
}