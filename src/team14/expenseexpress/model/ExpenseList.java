package team14.expenseexpress.model;

import java.util.ArrayList;
import java.util.Collections;

public class ExpenseList {

	private ArrayList<Expense> expenseList;
    /**
     * Constructor, intitializes ArrayList<Expense>
     *	
     * @return String uri
     */
	public ExpenseList() {
		this.expenseList = new ArrayList<Expense>();
	}
	 /**
     * Getter, retrieves ArrayList<Expense>
     *	
     * @return ArrayList<Expense> expenseList
     */
	public ArrayList<Expense> getExpenses() {
		return this.expenseList;
	}
	 /**
     * method adds Expense Object to ArrayList<Expense>
     *	
     * @param Expense expense
     */
	public void add(Expense newExpense) {
		this.expenseList.add(newExpense);
	}
	 /**
     * method removes Expense Object from ArrayList<Expense>
     *	
     * @param Expense expense
     */
	public void remove(Expense oldExpense) {
		this.expenseList.remove(oldExpense);
	}
	 /**
     * method gets ArrayList<Expense> size
     *	
     * @return int size
     */
	public int size() {
		return this.expenseList.size();
	}
	 /**
     * getter , retrieves Exepense expense from
     *	ArrayList<Expense> at an int position
     * @param int position
     */
	public Expense get(int position){
		return this.expenseList.get(position);
	}
	 /**
     * method sorts Expense Objects in ArrayList<Expense>
     *	by there date of entry (Ids)
     * 
     */
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(expenseList,new Expense.ExpenseComparator());
	}
	/**
	 * empties the expenseList
	 */
	public void clear() {
		expenseList.clear();
	}
}