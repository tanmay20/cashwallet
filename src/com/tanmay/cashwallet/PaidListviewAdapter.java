package com.tanmay.cashwallet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PaidListviewAdapter extends BaseAdapter {

	private JSONArray dataArray;
	private Activity activity;
	
	private static LayoutInflater inflater =null;
	
	public PaidListviewAdapter(JSONArray jsonArray, Activity a)
	{
		this.dataArray = jsonArray;
		this.activity =a;
		
		
		inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.dataArray.length();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// set up convert view if it is null
		ListCell cell;
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.paidlist_cell,null);
			cell = new ListCell();
			cell.ReceiverID = (TextView) convertView.findViewById(R.id.receiver);
			cell.Amount = (TextView) convertView.findViewById(R.id.amnt);			
			cell.TrID = (TextView) convertView.findViewById(R.id.trid);
			convertView.setTag(cell);
		}
		else
		{
			cell = (ListCell) convertView.getTag();
		}
		
		//Change Date of cell
		try 
		{
			JSONObject jsonObject = this.dataArray.getJSONObject(position);
			cell.ReceiverID.setText("Paid To:"+jsonObject.getString("reciverid"));
  			cell.Amount.setText("Amount:"+jsonObject.getString("amount"));
			cell.TrID.setText("TransID:"+jsonObject.getInt("trid"));
		}
		
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		return convertView;
	} 
	private class ListCell
	{
		private TextView ReceiverID;
		private TextView Amount;		
		private TextView TrID;
		
	}
}
