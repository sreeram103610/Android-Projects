package com.maadlabs.peepsync;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	Button login_btn, signup_btn;
	EditText user_name_box, password_box;
	OnClickListener btn_listener;
	HttpURLConnection urlConnection;
	String line;
	HttpURLConnection con;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		setResources();
		setListener();
		login_btn.setOnClickListener(btn_listener);
	}
	
	public void setResources()
	{
		login_btn = (Button) findViewById(R.id.buttonLoginNext);
		user_name_box = (EditText) findViewById(R.id.editTextLoginUser);
		password_box = (EditText) findViewById(R.id.editTextLoginPassword);
		
	}
	
	public void setListener()
	{
		btn_listener = new OnClickListener()
		{
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.buttonLoginNext:
			{
				Thread th = new Thread()
				{
					@Override
					public void run()
					{
						HTTPConnect(user_name_box.getText().toString(), password_box.getText().toString());
					}
				};
				th.start();
			}
			}
			}
		};
	}
	
	private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
	{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (NameValuePair pair : params)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	    }

	    return result.toString();
	}
	
	private void readStream(InputStream in, final HttpURLConnection con) {
		  BufferedReader reader = null;
		  try {
		    reader = new BufferedReader(new InputStreamReader(in));
		    line = "";
		    while ((line = reader.readLine()) != null) {
		    	Log.i("output", line);
		    }
		  } catch (IOException e) {
		    e.printStackTrace();
		  } finally {
		    if (reader != null) {
		      try {
		        reader.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		        }
		    }
		  }
	}
		  
	public void HTTPConnect(String username, String password)
	{
		try 
		{
			URL url = new URL("http://140.254.191.237/test/");
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(100000);
			con.setConnectTimeout(150000);
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));

			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

			con.connect();
			Log.i("Connection Status", con.getResponseMessage());
			readStream(con.getInputStream(), con);
	
		}catch (Exception e) {
		  	e.printStackTrace();
		}

	}
}