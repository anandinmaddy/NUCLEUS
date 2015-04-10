package com.nucleus.jsonrequest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nucleus.jsonrequest.RestClient.RequestMethod;

import android.net.http.AndroidHttpClient;
import android.util.Log;

@SuppressWarnings({ "deprecation", "unused" })
public class JsonParser {
	
	InputStream inputStream = null;
    String result = "";
	  static String json = "MyDay";
	 
JSONArray jarray=null;
	  public JsonParser()
	  {
	  }

	  
	public void getJsonFromUrl(String url) {
		// TODO Auto-generated method stub
		
		RestClient client = new RestClient(url);
		
		
		//appendLog("Sending Data to Rest"+jsonSearch);
		//client.AddHeader("Content-Type", "application/json");
		try {
			client.Execute(RequestMethod.GET);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	String	response = client.getResponse();
	System.out.println("REsult"+response);
	}
		
}

	
	
	  
	  


