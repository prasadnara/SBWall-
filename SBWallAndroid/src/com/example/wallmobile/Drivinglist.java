package com.example.wallmobile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.wallmobile.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.widget.ListView;

public class Drivinglist extends ListActivity {
	/** Called when the activity is first created. */
	private static final String TAG = "MyActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,PLACES));
		android.widget.ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		final CharSequence[] items = {"Red", "Green", "Blue"};
		AlertDialog.Builder builder = new AlertDialog.Builder(Drivinglist.this);
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
				// create a view first	
				View pop_view = new View(Drivinglist.this);
				Intent location_intent = new Intent(view.getContext(), MapTabView.class);
				
				location_intent.putExtra("id", id);
				Bundle b = new Bundle();	
				b.putLong("id", id);
				location_intent.putExtras(b);		
				startActivityForResult(location_intent, 0);
			}
		});
	}
	static final String[] PLACES = new String[] {
		"Stonybrook rail station", "Indoor Sports Complex", "Student health center", "Stony Brook union", "Staller Center for arts", "Frank Melville Jr.memorial Library", "Charles B. Wang Asian American center", "Basketball", "University Hospital", "Student activity center", "KennethP.Lavalle stadium", "Administration", "Cafetaria", "Career Center", "Chapin apartments", "Computer science", "Graduate School", 
	  };
}