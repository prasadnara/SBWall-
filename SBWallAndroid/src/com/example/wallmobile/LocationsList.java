package com.example.wallmobile;

import com.example.wallmobile.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;

public class LocationsList extends ListActivity {
	/** Called when the activity is first created. */
	private static final String TAG = "MyActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Resources res = getResources();
		String[] locations = res.getStringArray(R.array.locationslist);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, locations));
		android.widget.ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		final CharSequence[] items = {"Red", "Green", "Blue"};
		AlertDialog.Builder builder = new AlertDialog.Builder(LocationsList.this);
		builder.setTitle("Pick a color");
		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		    }
		});
		final AlertDialog alert = builder.create();
		
		//OnItemClickListener
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				
				// when clicked we want to open Location page with location name
			
				//checking how to use popups
	/*			AlertDialog.Builder adb=new AlertDialog.Builder(LocationsList.this);
				adb.setTitle("LVSelectedItemExample");
				adb.setMessage("Selected Item is = "+id);
				adb.setPositiveButton("Ok", null);
				adb.show();
	*/	
				
				// create a view first	
				View pop_view = new View(LocationsList.this);
				Intent location_intent = new Intent(view.getContext(), LocationPage.class);
				//Intent sample = new Intent(view.getContext(), wallpost.class);
//				Log.i(TAG, "onListItemClick id=" + id);

				location_intent.putExtra("id", id);
				
				Bundle b = new Bundle();	
				b.putLong("id", id);
				location_intent.putExtras(b);		
				
				startActivityForResult(location_intent, 0);
			}
		});
	}
}