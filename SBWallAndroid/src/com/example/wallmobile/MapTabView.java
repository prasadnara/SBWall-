package com.example.wallmobile;
import java.io.BufferedInputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ZoomControls;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MapTabView extends MapActivity {
	/** Called when the activity is first created. */
	
//	EditText		txted			= null;
	
	Button			btnSimple		= null;
	
	MapView			gMapView		= null;
	
	MapController	mc				= null;
	
	Drawable		defaultMarker	= null;
	
	GeoPoint		p				= null;
	
	double			latitude		= 40.912422, longitude = -73.122055;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maptabview);
		
		// Creating TextBox displying Lat, Long
/*		txted = (EditText) findViewById(R.id.id1);
		String currentLocation = "Lat: " + latitude + " Lng: " + longitude;
		txted.setText(currentLocation);*/
		
		// Creating and initializing Map
		gMapView = (MapView) findViewById(R.id.myGMap);
		p = new GeoPoint((int) (latitude * 1000000), (int) (longitude * 1000000));
		gMapView.setSatellite(true);
		mc = gMapView.getController();
		mc.setCenter(p);
		mc.setZoom(14);
		
		// Add a location mark
//		MyLocationOverlay myLocationOverlay = new MyLocationOverlay();
//		List<Overlay> list = gMapView.getOverlays();
//		list.add(myLocationOverlay);
		
		// Adding zoom controls to Map
		ZoomControls zoomControls = (ZoomControls) gMapView.getZoomControls();
		zoomControls.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		gMapView.addView(zoomControls);
		gMapView.displayZoomControls(true);
		
		//add
		double src_lat = latitude; // the testing source
		double src_long = longitude; 

		double dest_lat=0;
		double dest_long=0;			

		//add
		Bundle extras = getIntent().getExtras();
		Long s = extras.getLong("id");

		if (s==0)
		{
			//Stony Brook railway station
			dest_lat = 40.92042; // the testing destination
			dest_long = -73.128562;			
		}
		if (s==1)
		{
			//Indoor Sports Complex
			dest_lat = 40.917968; // the testing destination
			dest_long = -73.125403;
		}
		if (s==2)
		{
			//student Health center
			dest_lat = 40.919269; // the testing destination
			dest_long = -73.121846;	
		}
		if (s==3)
		{
			//Stony Brook Union
			dest_lat = -73.122661; // the testing destination
			dest_long = 40.91721;			
		}
		if (s==4)
		{
			//Staller Center of Arts
			dest_lat = 40.915994; // the testing destination
			dest_long = -73.121223;
		}
		if (s==5)
		{
			//Frank Melville Library
			dest_lat = 40.915426; // the testing destination
			dest_long = -73.122854;
		}
		if (s==6)
		{
			//Charles Wang Center
			dest_lat = 40.915799; // the testing destination
			dest_long = -73.119485;
		}
		if (s==7)
		{
			//Basket Ball
			dest_lat = 40.918231; // the testing destination
			dest_long = -73.126459 ;	
		}
		if (s==8)
		{
			//Health Science Center
			dest_lat = 40.910672; // the testing destination
			dest_long = -73.114846;
		}
		if (s==9)
		{
			//SAC
			dest_lat = 40.91421; // the testing destination
			dest_long = -73.123648;
		}
		if (s==10)
		{
			//Kenneth P Lavalle Stadium
			dest_lat = 40.918831; // the testing destination
			dest_long = -73.123906 ;	
		}
		if (s==11)
		{
			//Administration
			dest_lat = 40.914729; 
			dest_long = -73.120043;
		}
		if (s==12)
		{
			//Cafetaria
			dest_lat = 40.911015; // the testing destination
			dest_long = -73.123627;
		}
		if (s==13)
		{
			//Career Center
			dest_lat = 40.915426;
			dest_long = -73.122854;
		}
		if (s==14)
		{
			//Chapin Apts
			dest_lat = 40.90718; // the testing destination
			dest_long = -73.109765;	
		}
		if (s==15)
		{
			//COmputer Science
			dest_lat = 40.912422;
			dest_long = -73.122055;	
		}
		if (s==16)
		{
			//Graduate School
			dest_lat = 40.9123;
			dest_long = -73.12161;	
		}

		
		GeoPoint srcGeoPoint = new GeoPoint((int) (src_lat * 1E6),
		(int) (src_long * 1E6));
		GeoPoint destGeoPoint = new GeoPoint((int) (dest_lat * 1E6),
		(int) (dest_long * 1E6));

		DrawPath(srcGeoPoint, destGeoPoint, Color.GREEN, gMapView);

		gMapView.getController().animateTo(srcGeoPoint);
		gMapView.getController().setZoom(15);

		// Getting locationManager and reflecting changes over map if distance travel by
		// user is greater than 500m from current location.
//		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, this);
	}
	
	/* This method is called when use position will get changed */
	public void onLocationChanged(Location location) {
		/*		if (location != null) {
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			String currentLocation = "Lat: " + lat + " Lng: " + lng;
			txted.setText(currentLocation);
			p = new GeoPoint((int) lat * 1000000, (int) lng * 1000000);
			mc.animateTo(p);
		}*/
	}
	
	public void onProviderDisabled(String provider) {
		// required for interface, not used
	}
	
	public void onProviderEnabled(String provider) {
		// required for interface, not used
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// required for interface, not used
	}
	
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* User can zoom in/out using keys provided on keypad */
/*	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_I) {
			gMapView.getController().setZoom(gMapView.getZoomLevel() + 1);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_O) {
			gMapView.getController().setZoom(gMapView.getZoomLevel() - 1);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_S) {
			gMapView.setSatellite(true);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_T) {
			gMapView.setTraffic(true);
			return true;
		}
		return false;
	}*/
	
	/* Class overload draw method which actually plot a marker,text etc. on Map */
	/*protected class MyLocationOverlay extends com.google.android.maps.Overlay {
		
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			Paint paint = new Paint();
			
			super.draw(canvas, mapView, shadow);
			// Converts lat/lng-Point to OUR coordinates on the screen.
			Point myScreenCoords = new Point();
			mapView.getProjection().toPixels(p, myScreenCoords);
			
			paint.setStrokeWidth(1);
			paint.setARGB(255, 255, 255, 255);
			paint.setStyle(Paint.Style.STROKE);
			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
			
			canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
			canvas.drawText("I am here...", myScreenCoords.x, myScreenCoords.y, paint);
			return true;
		}
	}*/
	//add
	private void DrawPath(GeoPoint src, GeoPoint dest, int color,MapView mMapView01) 
	{

		// connect to map web service
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en");
		urlString.append("&saddr=");//from
		urlString.append( Double.toString((double)src.getLatitudeE6()/1.0E6 ));
		urlString.append(",");
		urlString.append( Double.toString((double)src.getLongitudeE6()/1.0E6 ));
		urlString.append("&daddr=");//to
		urlString.append( Double.toString((double)dest.getLatitudeE6()/1.0E6 ));
		urlString.append(",");
		urlString.append( Double.toString((double)dest.getLongitudeE6()/1.0E6 ));
		urlString.append("&ie=UTF8&0&om=0&output=kml");
		Log.d("xxx","URL="+urlString.toString());
		// get the kml (XML) doc. And parse it to get the coordinates(direction route).
		Document doc = null;
		HttpURLConnection urlConnection= null;
		URL url = null;
		try
		{ 
		url = new URL(urlString.toString());
		urlConnection=(HttpURLConnection)url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.connect(); 

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		doc = db.parse(urlConnection.getInputStream()); 

		if(doc.getElementsByTagName("GeometryCollection").getLength()>0)
		{
		//String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName();
		String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ;
		Log.d("xxx","path="+ path);
		String [] pairs = path.split(" "); 
		String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height
		// src
		GeoPoint startGP = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
		mMapView01.getOverlays().add(new MyOverLay(startGP,startGP,1));
		GeoPoint gp1;
		GeoPoint gp2 = startGP; 
		for(int i=1;i<pairs.length;i++) // the last one would be crash
		{
		lngLat = pairs[i].split(",");
		gp1 = gp2;
		// watch out! For GeoPoint, first:latitude, second:longitude
		gp2 = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
		mMapView01.getOverlays().add(new MyOverLay(gp1,gp2,2,color));
		Log.d("xxx","pair:" + pairs[i]);
		}
		mMapView01.getOverlays().add(new MyOverLay(dest,dest, 3)); // use the default color
		} 
		}
		catch (MalformedURLException e)
		{
		e.printStackTrace();
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
		e.printStackTrace();
		}
		catch (SAXException e)
		{
		e.printStackTrace();
		}
	}
}
