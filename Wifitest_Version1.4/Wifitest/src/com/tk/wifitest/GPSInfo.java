package com.tk.wifitest;



import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;



public class GPSInfo extends Activity {
	
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;    
    
    protected LocationManager locationManager;
    
    protected Button retrieveLocationButton;
    
    Calendar c = Calendar.getInstance();
	Date date = c.getTime();
	TextView GPSmessage;   
	Switch sw;
	AtomicBoolean ContinueThread=new AtomicBoolean(false);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpsinfo);		
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new mylocationlistener());   
        //showCurrentLocation();	
        sw = (Switch) findViewById(R.id.switch1);
	sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		@Override
	      public void onCheckedChanged(CompoundButton buttonView,boolean ischecked) {
			if(ischecked){
				Toast.makeText(getApplicationContext(), "Logging data started",
                        Toast.LENGTH_LONG).show();
                //Start the thread, thread will sleep for 5sec and invokes the method to runBackbackground after 5sec
				Thread runBackground=new Thread(new Runnable() {			
					@Override
					public void run() {
						try{
							while(ContinueThread.get()){
								Thread.sleep(5000);
								handler.sendMessage(handler.obtainMessage());
							}					
						}catch(Throwable t){					
						}					
					}
				});
				  ContinueThread.set(true);
				  runBackground.start();
			}else{
				Thread runBackground=new Thread(new Runnable() {			
					@Override
					public void run() {
						try{
							while(ContinueThread.get()){
								Thread.sleep(5000);
								handler.sendMessage(handler.obtainMessage());
							}					
						}catch(Throwable t){					
						}					
					}
				});
				  ContinueThread.set(false);
				  runBackground.start();
				  runBackground.interrupt();
                  runBackground=null;
              	
                  Toast.makeText(getApplicationContext(), "Logging data stopped",Toast.LENGTH_LONG).show();
				
			}
		}
		
	});
	} 

	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg){
			showCurrentLocation(); 
		}
	};
	 protected void showCurrentLocation() 
	    {

	        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

	        if (location != null) 
	        {
	            String message = String.format("Current Location & Time \n Longitude: %1$s \n Latitude: %2$s \n Time: %3$s \n Clock when fetched: %4$s",location.getLongitude(), location.getLatitude(), System.currentTimeMillis(), date);
	            GPSmessage = (TextView)findViewById(R.id.textView2);
	            GPSmessage.setText(message);
	            Toast.makeText(GPSInfo.this, "GPSInfo:Current Location..",Toast.LENGTH_SHORT).show();
	        }

	    }
	
	
	 private class mylocationlistener implements LocationListener
	    {				
				@Override
				public void onLocationChanged(Location location) 
				{
					String message = String.format("New Location & Time \n Longitude: %1$s \n Latitude: %2$s \n Time: %3$s \n Clock when fetched: %4$s",location.getLongitude(), location.getLatitude(), System.currentTimeMillis(), date);
		            Toast.makeText(GPSInfo.this, "GPSInfo:Location Change detected, Re-Calculating co-ordinates..", Toast.LENGTH_SHORT).show();
					GPSmessage.setText(message);
				}

				@Override
				public void onStatusChanged(String provider, int status,Bundle extras) 
				{
					 Toast.makeText(GPSInfo.this, "GPSInfo:Provider status changed",Toast.LENGTH_SHORT).show();
					
				}

				@Override
				public void onProviderEnabled(String provider) 
				{
					 Toast.makeText(GPSInfo.this,"GPSInfo:Provider enabled by the user. GPS turned on",Toast.LENGTH_SHORT).show();
					
				}

				@Override
				public void onProviderDisabled(String provider) 
				{
					 Toast.makeText(GPSInfo.this,"GPSInfo:Provider disabled by the user. GPS turned off", Toast.LENGTH_SHORT).show();
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
		
		 menu.add(0,Menu.FIRST+3,Menu.NONE, "Check Data").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(GPSInfo.this, DBCheck.class));
				return true;
			}
		});		 
		 return super.onCreateOptionsMenu(menu);		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	  /*public void onStart(){
		  super.onStart();
		  Thread runBackground=new Thread(new Runnable() {			
			@Override
			public void run() {
				try{
					while(ContinueThread.get()){
						Thread.sleep(5000);
						handler.sendMessage(handler.obtainMessage());
					}					
				}catch(Throwable t){					
				}					
			}
		});
		  ContinueThread.set(true);
		  runBackground.start();
	  }*/
}
