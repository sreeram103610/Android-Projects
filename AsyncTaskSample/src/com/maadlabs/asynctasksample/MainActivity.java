package com.maadlabs.asynctasksample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressDialog progressbar;
	private Button start_task, reset_text;
	public TextView newtext;
	private OnClickListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		newtext = (TextView) findViewById(R.id.outputtext);
		reset_text = (Button) findViewById(R.id.resettext);
		start_task = (Button) findViewById(R.id.startTask);
		
		listener = new OnClickListener()
		{
			public void onClick(View v)
			{
				if(v.getId() == R.id.startTask)
				{
				progressbar = new ProgressDialog(MainActivity.this);
				LongAsyncTask ltask = new LongAsyncTask();
				ltask.execute(1);
				}
				else
				{
					newtext.setText("Text Reseted");
				}
			}
		};
		
		reset_text.setOnClickListener(listener);
		start_task.setOnClickListener(listener);
		
		
	}

	public class LongAsyncTask extends AsyncTask<Integer, Integer, String>
	{

		public void onPreExecute()
		{
			progressbar.setCancelable(false);
			progressbar.setMessage("Loading..");
			progressbar.setMax(10);
			progressbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressbar.show();
		}
		@Override
		protected String doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			if(arg0[0] == 1)
			{
			for(int i = 0; i < 10; i++)  // some long running task
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress(i+1); // sends i+1 to onProgressUpdate()
			}
			if(!isCancelled())
				return "Task Completed";
			}
			return "Task Not Done";
		}
		 protected void onProgressUpdate(Integer... progress) {
	         progressbar.setProgress(progress[0]);
	     }

	     protected void onPostExecute(String result) {
	    	 progressbar.dismiss();
	         newtext.setText(result);
	     }
		
	}

}
