
/* Author: Shreyas Srinivasa */


package com.tk.wifitest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView mainText;
	WifiManager mainWifi;
	WifiReceiver receiverWifi;
	List<ScanResult> wifiList;
	StringBuilder sb = new StringBuilder();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainText = (TextView) findViewById(R.id.textView2);
		  mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		  receiverWifi = new WifiReceiver();
		  registerReceiver(receiverWifi, new IntentFilter(
		  WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		  mainWifi.startScan();
		  mainText.setText("\nStarting Scan...\n");
				
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		 menu.add(0, 0, 0, "Refresh");
		 return super.onCreateOptionsMenu(menu);}
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
		  mainWifi.startScan();
		  mainText.setText("Starting Scan");
		  return super.onMenuItemSelected(featureId, item);}
		 protected void onPause() {
		   unregisterReceiver(receiverWifi);
		   super.onPause();
		 }
	
		 
		 class WifiReceiver extends BroadcastReceiver {
			  public void onReceive(Context c, Intent intent) {
			  sb = new StringBuilder();
			  
			  Calendar g = Calendar.getInstance();
			  Date date = g.getTime();
			  
			  wifiList = mainWifi.getScanResults();
			  int sizenum = wifiList.size();
			  Toast.makeText(getApplicationContext(),"Wifi Networks Found:" + sizenum, Toast.LENGTH_SHORT).show();
			  
			  for(int i = 0; i < wifiList.size(); i++){
			     sb.append(new Integer(i+1).toString() + ".");
			     sb.append("SSID:"+(wifiList.get(i)).SSID.toString());
			     sb.append("\nSignal Strength:"+(wifiList.get(i)).level+"dBm");
			     sb.append(("\nTimestamp in millis:"+(wifiList.get(i)).timestamp));
			     sb.append("\nClock when fetched:"+date);
			     sb.append("\n");
			     sb.append("\n");
			}
			mainText.setText(sb);
			
			}

			
	
		 }}
