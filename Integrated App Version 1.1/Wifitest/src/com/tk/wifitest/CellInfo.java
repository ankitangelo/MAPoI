/* Author: Shreyas Srinivasa */

package com.tk.wifitest;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;




public class CellInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cell_info);
		
		fetchinfo();
		
	}
        
		private void fetchinfo() {
			TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

			String networkOperator = telephonyManager.getNetworkOperator();
			int mcc;
			int mnc;
			int cid = cellLocation.getCid();
			int lac = cellLocation.getLac();
			Calendar g = Calendar.getInstance();
			  Date date = g.getTime();
			
			  
			 
			        mcc = Integer.parseInt(networkOperator.substring(0, 3));
			        mnc = Integer.parseInt(networkOperator.substring(3));
			  
			
			 
				TextView params = (TextView)findViewById(R.id.textView2);
				params.setText("Cellid:"+cid+"\nLocation Area Code:" +lac+"\nMCC:"+mcc+"\nMNC:"+mnc+"\nClock:"+date);
					
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, Menu.FIRST, Menu.NONE, "Refresh").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				Toast.makeText(getApplicationContext(),"Scanning... ", Toast.LENGTH_SHORT).show();
				fetchinfo();
						
				return true;
			}
		});
		

		menu.add(0, Menu.FIRST+1 , Menu.NONE, "WiFi AP Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(CellInfo.this, MainActivity.class));
				return true;
			}
		});
		
		menu.add(0,Menu.FIRST+2,Menu.NONE, "GPS-Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(CellInfo.this, GPSInfo.class));
				
				return true;
			}
		});
	
		
		 menu.add(0,Menu.FIRST+3,Menu.NONE, "Check Data").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(CellInfo.this, DBCheck.class));
				return true;
			}
		});
		 
		 
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
