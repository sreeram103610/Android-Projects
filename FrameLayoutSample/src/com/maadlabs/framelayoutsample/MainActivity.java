package com.maadlabs.framelayoutsample;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] options = {"Option-1", "Option-2", "Option-3"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, options);
        final ListView listView = (ListView) findViewById(R.id.dashBoardOptions);
        listView.setAdapter(adapter);
        listView.setVisibility(View.GONE);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);

        Button button = (Button) findViewById(R.id.showOptions);
        button.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View v) {
				if(listView.getVisibility() == View.GONE)
				{
					Log.i("point", "if");
					listView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_from_right));
					layout.setBackgroundColor(Color.parseColor("#000000"));
					layout.setAlpha(0.8f);
					listView.setVisibility(View.VISIBLE);
					
				}
				else
				{
					Log.i("point", "else");
					listView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_to_right));
					layout.setBackgroundColor(Color.parseColor("#ffffff"));
					layout.setAlpha(1f);
					listView.setVisibility(View.GONE);
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
    
}
