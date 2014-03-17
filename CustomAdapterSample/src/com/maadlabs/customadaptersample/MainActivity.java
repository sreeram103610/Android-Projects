package com.maadlabs.customadaptersample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ArrayList<Contact> contacts;
	Contact contact;
	CustomListAdapter adapter;
	ListView contact_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getContacts();
		adapter = new CustomListAdapter(MainActivity.this, R.layout.listitem_layout, contacts);
		contact_list = (ListView) findViewById(R.id.customlist);
		contact_list.setAdapter(adapter);
	}

	public void getContacts()
	{
		contacts = new ArrayList<Contact>();
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
		                ContactsContract.CommonDataKinds.Phone.NUMBER};

		Cursor people = getContentResolver().query(uri, projection, null, null, null);

		int index_name = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int index_number = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
		int index_id = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);

		people.moveToFirst();
		do {
			contact = new Contact();
		    contact.contact_name = people.getString(index_name);
		    contact.contact_number = people.getString(index_number);
		    contact.contact_image = getImage(people.getString(index_id));
		    contacts.add(contact);
		} while (people.moveToNext());
		Log.i("2", contacts.get(2).contact_name);
		

	}
	
	public Bitmap getImage(String contact_id)
	{

        Bitmap photo = null;
 
        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contact_id)));
 
            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            }
           
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return photo;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
