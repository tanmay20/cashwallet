package com.tanmay.cashwallet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tanmay.cashwallet.ApiSummary;
import com.tanmay.cashwallet.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Summary extends ActionBarActivity{

private TextView textViewrcvd;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        
        
        this.textViewrcvd = (TextView) this.findViewById(R.id.textViewrcvd);
        
        new GetSummaryTask().execute(new ApiSummary());
        
    }

    public void setTextToTextView(JSONArray jsonArray)
    {
    	String s = "";
    	for(int i=0; i<jsonArray.length();i++){
    		
    		JSONObject json = null;
    		try{
    			json = jsonArray.getJSONObject(i);
    			
    			s = s +
    					"Your Balance is Rs: "+json.getString("balance")+"\n\n";
    					
    					
    			
    					
    		}catch (JSONException e)
    		{
    			e.printStackTrace();
    		}
    	}
    	this.textViewrcvd.setText(s);
    }

    public class GetSummaryTask extends AsyncTask<ApiSummary,Long,JSONArray>
    {

		@Override
		protected JSONArray doInBackground(ApiSummary... params) {
			
			return params[0].GetAllUser();
		}

		@Override
		protected void onPostExecute(JSONArray jsonArray) {
			
			setTextToTextView(jsonArray);
		}
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
