package com.maadlabs.fragmentssample;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		changeFragmentText();
	}
	
	public void changeFragmentText()
	{
		Fragments f1 = (Fragments) getFragmentManager().findFragmentById(R.id.first_fragment);
		f1.setText("world");
	}

}
