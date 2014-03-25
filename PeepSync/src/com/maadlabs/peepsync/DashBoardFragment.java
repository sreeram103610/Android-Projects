package com.maadlabs.peepsync;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;

public class DashBoardFragment extends Fragment {

	ListView dashBoardList;
	View v;
	CustomListAdapter dashBoardAdapter;
	ArrayList<DashBoard> dashBoardOptions;
	OnClickListener buttonListener;
	Button showOptionsButton;
	ListView optionsList;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance)
	{
		dashBoardOptions = new ArrayList<DashBoard>();
		String options[] = { "Recent", "Connections", "Search", "Map", "Settings", "Logout" };
		for(int i = 0; i < options.length; i++)
		{
			dashBoardOptions.add(new DashBoard(options[i], null));
		}
		
		v = inflater.inflate(R.layout.fragment_dash_board, parent, false);
		dashBoardAdapter = new CustomListAdapter(v.getContext(), R.layout.board_option_layout, dashBoardOptions);
		dashBoardList = (ListView) v.findViewById(R.id.dashBoardOptions);
		dashBoardList.setPadding(20, 20, 20, 20);
		dashBoardList.setAdapter(dashBoardAdapter);
		return v;
	}
	
	public void onStart()
	{
		super.onStart();
		optionsList = (ListView) v.findViewById(R.id.dashBoardOptions);
		showOptionsButton = (Button) v.findViewById(R.id.showOptions);
		buttonListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				switch(v.getId())
				{
				case R.id.showOptions:
				{
					if(optionsList.getVisibility() == View.GONE)
					{ 
	 					optionsList.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_from_right));
	 					//mainLayout.setBackgroundColor(Color.parseColor("#00FF00"));
						optionsList.setVisibility(View.VISIBLE);
			
					}
					else
					{
						optionsList.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_to_right));
						//mainLayout.setBackgroundColor(Color.parseColor("#ffffff"));
						optionsList.setVisibility(View.GONE);
					}
				}
				}
				
			}
		};
		
		showOptionsButton.setOnClickListener(buttonListener);
	}
	


}
