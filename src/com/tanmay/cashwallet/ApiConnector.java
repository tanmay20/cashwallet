package com.tanmay.cashwallet;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class ApiConnector {
	public JSONArray GetAllUser()
	{
		// url for getting all users
		
		String url = "http://10.0.2.2/androidapp/receiverbasic.php";
		
		// Get HTTPResponse Object from URL
				// Get HTTPEntity from HTTP Response Object
				
				HttpEntity httpEntity = null;
		
		try
		{
			DefaultHttpClient httpClient = new DefaultHttpClient(); //Default HttpClient
			HttpGet httpGet = new HttpGet(url);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			httpEntity = httpResponse.getEntity();	
			
		}catch(ClientProtocolException e){
			
			// signals error in Http protocol
			
			e.printStackTrace();
			
			// log errors here
		
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
	//Convert HttpEntity to JSon Array
	
	JSONArray jsonArray = null;
		
	if(httpEntity != null){
		try {
			String entityResponse = EntityUtils.toString(httpEntity);
			Log.e("Entity Response : ", entityResponse);
			
			jsonArray = new JSONArray(entityResponse);
		
		} catch (JSONException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	return jsonArray;
	}
	

}
