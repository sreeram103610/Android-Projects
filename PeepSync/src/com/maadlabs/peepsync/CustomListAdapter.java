package com.maadlabs.peepsync;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<DashBoard> {

	Context context;
	int resource;
	ArrayList<DashBoard> options;
	
	public CustomListAdapter(Context context, int resource, ArrayList<DashBoard> options) {
		super(context, resource, options);
		this.context = context;
		this.resource = resource;
		this.options = options;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row;
		OptionHolder holder = null;
		
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(resource, parent, false);  // like a template to add values
			
			holder = new OptionHolder();
			holder.option_holder  = (TextView) row.findViewById(R.id.dash_board_option);
			holder.image_holder = (ImageView) row.findViewById(R.id.das_board_option_image);
			row.setTag(holder);
		}

		else
		{
			row = convertView;
			holder = (OptionHolder) row.getTag();
		}
		
		DashBoard option = options.get(position);
		holder.option_holder.setText(option.option);
		
		/*if(option.contact_image!=null)
			holder.image_holder.setImageBitmap(option.contact_image);
		else
		{	
			holder.image_holder.setImageResource(R.drawable.android);
		}*/
		return row;
	}


	
	static class OptionHolder
	{
		TextView option_holder;
		ImageView image_holder;
	}
	
}
