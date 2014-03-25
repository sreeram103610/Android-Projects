package com.maadlabs.peepsync;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	Button optionsButton;
	OnClickListener buttonListener;
	Fragment optionsFragment;
	FrameLayout mainLayout;
	ListView optionsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		optionsButton.setOnClickListener(buttonListener);
	}
	
	public void init()
	{
		optionsList = (ListView) findViewById(R.id.dashBoardOptions);
		mainLayout = (FrameLayout) findViewById(R.id.mainLayout);
		mainLayout.setOnTouchListener(new OnSwipeTouchListener(this)
		{
			public void onSwipeRight() {
				//optionsList.setX(x);
				//optionsList.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_to_right));
				//mainLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				optionsList.setVisibility(View.GONE);
		    }
		    public void onSwipeLeft() {
		    	//optionsList.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_right));
					//mainLayout.setBackgroundColor(Color.parseColor("#00FF00"));
				optionsList.setVisibility(View.VISIBLE);
		    }
		   
		});
		optionsButton = (Button) findViewById(R.id.showOptions);
		optionsFragment = (Fragment) getFragmentManager().findFragmentById(R.id.dashBoard);
	}
}
