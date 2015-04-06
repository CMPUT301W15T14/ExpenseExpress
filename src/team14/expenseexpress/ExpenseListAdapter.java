
package team14.expenseexpress;

import java.util.ArrayList;

import team14.expenseexpress.model.Claim;
import team14.expenseexpress.model.Expense;
import team14.expenseexpress.model.ExpenseList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExpenseListAdapter extends BaseAdapter {
	
	private static ArrayList<Expense> expenseList;
	private LayoutInflater mInflater;

	public ExpenseListAdapter(Context context, ArrayList<Expense> arrayList) {
		expenseList = arrayList;
		mInflater = LayoutInflater.from(context);
	}


	public int getCount() {
		return expenseList.size();
	}

	public Expense getItem(int position) {
		return expenseList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_expense_list, null);
			holder = new ViewHolder();
			holder.expense = (TextView) convertView.findViewById(R.id.expensename);
			holder.date = (TextView) convertView.findViewById(R.id.startdate);
			holder.category = (TextView) convertView.findViewById(R.id.expenseCategory);
			holder.amount = (TextView) convertView.findViewById(R.id.expenseAmount);
			holder.currency = (TextView) convertView.findViewById(R.id.expenseCurrency);
			holder.description = (TextView) convertView.findViewById(R.id.expenseDescript);
			holder.incomplete = (TextView) convertView.findViewById(R.id.incomplete);
			holder.yesreceipt = (TextView) convertView.findViewById(R.id.yesReceipt);
			holder.noreceipt = (TextView) convertView.findViewById(R.id.noReceipt);
			

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.expense.setText(expenseList.get(position).getName());
		holder.date.setText((String) android.text.format.DateFormat.format("yyyy-MM-dd",expenseList.get(position).getExpenseDate()));
		holder.category.setText(expenseList.get(position).getCategory());
		holder.amount.setText(Double.toString(expenseList.get(position).getAmount().getNumber()));
		holder.currency.setText(expenseList.get(position).getAmount().getCurrency().getName());
		holder.description.setText(expenseList.get(position).getDescription());
		if (expenseList.get(position).getIncomplete()){

			holder.incomplete.setVisibility(View.VISIBLE);
		}	///not correct boolean factors reversed
		else{
			holder.incomplete.setVisibility(View.INVISIBLE);

		}
		if (expenseList.get(position).getReceipt() != null){
			holder.yesreceipt.setVisibility(View.VISIBLE);
			holder.noreceipt.setVisibility(View.INVISIBLE);
		}	
		else{
			holder.noreceipt.setVisibility(View.VISIBLE);
			holder.yesreceipt.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	static class ViewHolder {
		TextView expense;
		TextView date;
		TextView category;
		TextView amount;
		TextView currency;
		TextView description;
		TextView complete;
		TextView incomplete;
		TextView yesreceipt;
		TextView noreceipt;
	}
}