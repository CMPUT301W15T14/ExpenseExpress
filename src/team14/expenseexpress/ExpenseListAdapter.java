
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
			convertView = mInflater.inflate(R.layout.custom_claim_list, null);
			holder = new ViewHolder();
			holder.expense = (TextView) convertView.findViewById(R.id.expensename);
			holder.date = (TextView) convertView.findViewById(R.id.expensedate);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.expense.setText(expenseList.get(position).getName());
		holder.date.setText(expenseList.get(position).getExpenseDate().toString());
		
		return convertView;
	}

	static class ViewHolder {
		TextView expense;
		TextView date;
	}
}