package com.tanmay.cashwallet;

import org.json.JSONArray;
import com.tanmay.cashwallet.ApiConnector;
import com.tanmay.cashwallet.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ReceivedList extends ActionBarActivity{
	
	private ListView listViewReceived;

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receivedlist);
        
        this.listViewReceived = (ListView) this.findViewById(R.id.listViewReceived);
        
        
        new GetAllUserTask().execute(new ApiConnector());
        
    }
	
	public void setListAdapter(JSONArray jsonArray)
	{
		this.listViewReceived.setAdapter(new ReceivedListViewAdapter(jsonArray,this));
	}
	private class GetAllUserTask extends AsyncTask<ApiConnector,Long,JSONArray>
    {

		@Override
		protected JSONArray doInBackground(ApiConnector... params) {
			
			return params[0].GetAllUser();
		}

		@Override
		protected void onPostExecute(JSONArray jsonArray) {
			
			setListAdapter(jsonArray);
			
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
