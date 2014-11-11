package com.tk.wifitest;



import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class GPSInfo extends Activity {
	
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
    
    
    protected LocationManager locationManager;
    
    protected Button retrieveLocationButton;
    
    Calendar c = Calendar.getInstance();
	Date date = c.getTime();
	TextView GPSmessage;
    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpsinfo);
		
		
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new mylocationlistener());   
        showCurrentLocation();
			
	}
	
	
	
	
	 protected void showCurrentLocation() 
	    {

	        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

	        if (location != null) 
	        {
	            String message = String.format("Current Location & Time \n Longitude: %1$s \n Latitude: %2$s \n Time: %3$s \n Clock when fetched: %4$s",location.getLongitude(), location.getLatitude(), System.currentTimeMillis(), date);
	            GPSmessage = (TextView)findViewById(R.id.textView2);
	            GPSmessage.setText(message);
	            Toast.makeText(GPSInfo.this, "Current Location..",Toast.LENGTH_LONG).show();
	        }

	    }
	
	
	 private class mylocationlistener implements LocationListener
	    {
				
				@Override
				public void onLocationChanged(Location location) 
				{
					String message = String.format("New Location & Time \n Longitude: %1$s \n Latitude: %2$s \n Time: %3$s \n Clock when fetched: %4$s",location.getLongitude(), location.getLatitude(), System.currentTimeMillis(), date);
		            Toast.makeText(GPSInfo.this, "Location Change detected, Calculating co-ordinates..", Toast.LENGTH_LONG).show();
					GPSmessage.setText(message);
				}

				@Override
				public void onStatusChanged(String provider, int status,Bundle extras) 
				{
					 Toast.makeText(GPSInfo.this, "Provider status changed",Toast.LENGTH_LONG).show();
					
				}

				@Override
				public void onProviderEnabled(String provider) 
				{
					 Toast.makeText(GPSInfo.this,"Provider enabled by the user. GPS turned on",Toast.LENGTH_LONG).show();
					
				}

				@Override
				public void onProviderDisabled(String provider) 
				{
					 Toast.makeText(GPSInfo.this,"Provider disabled by the user. GPS turned off", Toast.LENGTH_LONG).show();
					
				}
	
	
	
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, Menu.FIRST, Menu.NONE, "Refresh").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showCurrentLocation();
				return true;
			}
		});
		
				
		menu.add(0, Menu.FIRST+1 , Menu.NONE, "WiFi AP Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(GPSInfo.this, MainActivity.class));
				return true;
			}
		});
		
		menu.add(0,Menu.FIRST+2,Menu.NONE, "Cell-Tower Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(GPSInfo.this, CellInfo.class));
				return true;
			}
		});
		
		
		
		
		
		 menu.add(0,Menu.FIRST+3,Menu.NONE, "Check Data");
		 return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
