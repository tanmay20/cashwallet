package com.tanmay.cashwallet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.tanmay.cashwallet.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pay extends Activity{

	EditText ePayFrom,ePayTo,eAmount;
	Button bPay;
	@SuppressLint("NewApi")
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        setContentView(R.layout.pay);
        
        ePayFrom = (EditText) findViewById(R.id.editTextPayfrom);
        
        ePayTo = (EditText) findViewById(R.id.editTextPayTo);
        
        eAmount = (EditText)findViewById(R.id.editTextAmount);
        
        bPay = (Button) findViewById(R.id.buttonPay);
        
        bPay.setOnClickListener(new View.OnClickListener() {
			
        	InputStream is = null;
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String payfrom = ""+ePayFrom.getText().toString();
				String payto = ""+ePayTo.getText().toString();
				String amnt = ""+eAmount.getText().toString();
				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
				nameValuePairs.add(new BasicNameValuePair("payerid" ,payfrom));
				nameValuePairs.add(new BasicNameValuePair("reciverid" ,payto));
				nameValuePairs.add(new BasicNameValuePair("amount" ,amnt));
				
				try
				{
					HttpClient httpClient = new DefaultHttpClient();
					
					HttpPost httpPost = new HttpPost("http://10.0.2.2/androidapp/pay.php");
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity entity = response.getEntity();
					
					is = entity.getContent();
					
					String msg = "Paid Successfully";
					Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
				}catch (ClientProtocolException e)
				{
					Log.e("ClientProtocol", "Log_tag");
					e.printStackTrace();
				}catch (IOException e)
				{
					Log.e("Log_tag", "IOException");
					e.printStackTrace();
				}
						
			}
		});
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
