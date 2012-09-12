package com.example.wallmobile;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GPS extends Activity 
	{
	    private LocationManager lm;
	    private LocationListener locationListener;

	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.maptabview); 
	        
	        //---use the LocationManager class to obtain GPS locations---
	        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	        LocationListener myLocationListener = new CurrentLocationListener();

	        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, myLocationListener);

	        Location currentLocation = locationManager.getLastKnownLocation("gps");

	        double     Latitude = currentLocation.getLatitude();
	        double     Longitude = currentLocation.getLongitude();        
	    }
	    
	    public class CurrentLocationListener implements LocationListener{
	    	@Override
	    	public void onLocationChanged(Location argLocation)
	    	{
	    	if (argLocation != null)
	    	{
	    		
	                Toast.makeText(getBaseContext(), 
	                    "Location changed : Lat: " + argLocation.getLatitude() + 
	                    " Lng: " + argLocation.getLongitude(), 
	                    Toast.LENGTH_SHORT).show();
	            }
	    	}
	    	@Override
	    	public void onProviderDisabled(String provider) {
	    	}
	    	@Override
	    	public void onProviderEnabled(String provider) {
	    	}
	    	@Override
	    	public void onStatusChanged(String provider, int status, Bundle arg2) {
	    	}
	    	} 
	}