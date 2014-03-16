package com.maadlabs.notificationsample;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceiveCustomBroadCast extends BroadcastReceiver{
	
	String CUSTOM_INTENT = "com.maadlabs.intent.CUSTOM_INTENT";
	String data;
	@Override
		 public void onReceive(Context context, Intent intent) {
		        if (intent.getAction().equals(CUSTOM_INTENT)) {
		        	data = "Received Intent";
		        	createNotification(context, data);
		        }
		    }
       
    @SuppressLint("NewApi")
  		public void createNotification(Context context, String data) {
    	
  	    		    Intent intent = new Intent(context, MainActivity.class);
  	    		    intent.putExtra("from", "notification");
  	    		    PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
  	    		   
  	    		   
  	    		    Notification new_notification = new Notification.Builder(context)
  	    		        .setContentTitle(data)
  	    		        .setContentText("<no text to display>")
  	    		        .setSmallIcon(R.drawable.ic_launcher)
  	    		        .setContentIntent(pIntent)  // the activity to start after the notification is clicked
  	    		        .build();
  	    		    
  	    		    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
  	    		    new_notification.flags |= Notification.FLAG_AUTO_CANCEL;  // cancel notification after it is selected

  	    		    notificationManager.notify(0, new_notification);

  	    		  }
}