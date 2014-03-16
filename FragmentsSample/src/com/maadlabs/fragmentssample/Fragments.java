package com.maadlabs.fragmentssample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragments extends Fragment{
	
	TextView tv;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance)
	{
		View v;
		v = inflater.inflate(R.layout.main_fragment_layout, parent, false);
		tv = (TextView) v.findViewById(R.id.fragment_text);
		return v;
	}
	
	
	public void setText(String text)
	{
		tv.setText(text);
	}
}
