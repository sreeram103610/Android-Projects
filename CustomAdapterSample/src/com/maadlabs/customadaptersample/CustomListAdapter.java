package com.maadlabs.customadaptersample;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<Contact> {

	Context context;
	int resource;
	ArrayList<Contact> contacts;
	
	public CustomListAdapter(Context context, int resource, ArrayList<Contact> contacts) {
		super(context, resource, contacts);
		this.context = context;
		this.resource = resource;
		this.contacts = contacts;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row;
		ContactHolder holder = null;
		
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(resource, parent, false);  // like a template to add values
			
			holder = new ContactHolder();
			holder.contactname_holder  = (TextView) row.findViewById(R.id.contact_name);
			holder.contactnumber_holder = (TextView) row.findViewById(R.id.contact_number);
			holder.contactphoto_holder = (ImageView) row.findViewById(R.id.contact_image);
			row.setTag(holder);
		}

		else
		{
			row = convertView;
			holder = (ContactHolder) row.getTag();
		}
		
		Contact contact = contacts.get(position);
		holder.contactname_holder.setText(contact.contact_name);
		holder.contactnumber_holder.setText(contact.contact_number);
		if(contact.contact_image!=null)
			holder.contactphoto_holder.setImageBitmap(contact.contact_image);
		else
		{	
			holder.contactphoto_holder.setImageResource(R.drawable.android);
		}
		return row;
	}


	
	static class ContactHolder
	{
		ImageView contactphoto_holder;
		TextView contactname_holder;
		TextView contactnumber_holder;
	}
	
}
