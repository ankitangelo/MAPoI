package com.example.mygeolocation;

/*Author Shashank Sridhar */

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	
	    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
	    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
	    
	    protected LocationManager locationManager;
	    
	    protected Button retrieveLocationButton;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        retrieveLocationButton = (Button) findViewById(R.id.rlb);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new mylocationlistener());   
        retrieveLocationButton.setOnClickListener(new OnClickListener() 
        {
                @Override
                public void onClick(View v) 
                {
                    showCurrentLocation();
                }
        });       
    }
    
    protected void showCurrentLocation() 
    {

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) 
        {
            String message = String.format("Current Location & Time \n Longitude: %1$s \n Latitude: %2$s \n Time: %3$s",location.getLongitude(), location.getLatitude(), System.currentTimeMillis());
            Toast.makeText(MainActivity.this, message,Toast.LENGTH_LONG).show();
        }

    }

	private class mylocationlistener implements LocationListener
    {

			@Override
			public void onLocationChanged(Location location) 
			{
				String message = String.format("New Location & Time \n Longitude: %1$s \n Latitude: %2$s \n Time: %3$s",location.getLongitude(), location.getLatitude(), System.currentTimeMillis());
	            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onStatusChanged(String provider, int status,Bundle extras) 
			{
				 Toast.makeText(MainActivity.this, "Provider status changed",Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onProviderEnabled(String provider) 
			{
				 Toast.makeText(MainActivity.this,"Provider enabled by the user. GPS turned on",Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onProviderDisabled(String provider) 
			{
				 Toast.makeText(MainActivity.this,"Provider disabled by the user. GPS turned off", Toast.LENGTH_LONG).show();
				
			}
			
    }  
    
}

        
        
          
