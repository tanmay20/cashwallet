package com.tanmay.cashwallet;

import org.json.JSONArray;
import com.tanmay.cashwallet.ApiPaid;
import com.tanmay.cashwallet.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class PaidHistory extends ActionBarActivity{
	
	private ListView listViewPaid;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paidlist);
        
        this.listViewPaid = (ListView) this.findViewById(R.id.listViewPaid);
        
        
        new GetAllPaidTask().execute(new ApiPaid());
        
    }
	public void setListAdapter(JSONArray jsonArray)
	{
		this.listViewPaid.setAdapter(new PaidListviewAdapter(jsonArray,this));
	}
	private class GetAllPaidTask extends AsyncTask<ApiPaid,Long,JSONArray>
    {

		@Override
		protected JSONArray doInBackground(ApiPaid... params) {
			
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
