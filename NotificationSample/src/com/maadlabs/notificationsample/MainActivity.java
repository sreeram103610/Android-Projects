package com.maadlabs.notificationsample;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	String CUSTOM_INTENT = "com.maadlabs.intent.CUSTOM_INTENT";
	Intent i;
	Button send_bcast;
	Bundle extras;
	ReceiveCustomBroadCast broadcast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		broadcast = new ReceiveCustomBroadCast();
		this.registerReceiver(broadcast, new IntentFilter(CUSTOM_INTENT));
		i = new Intent();
		i.setAction(CUSTOM_INTENT);
		send_bcast = (Button) findViewById(R.id.send_broadcast);
		send_bcast.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				MainActivity.this.sendBroadcast(i);
			}
			
		});
		
		extras = getIntent().getExtras();
		if(extras!=null && extras.getString("from").equals("notification"))
		{
			Toast.makeText(this, "From notification", Toast.LENGTH_SHORT).show();
		}
		
		
	}
}
