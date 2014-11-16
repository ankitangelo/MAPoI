
/* Author: Shreyas Srinivasa */


package com.tk.wifitest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {
	
	//private final String LOG_TAG = getClass().getSimpleName();
	
	DatabaseHelper dbHelper;
	
	TextView mainText;
	WifiManager mainWifi;
	WifiReceiver receiverWifi;
	List<ScanResult> wifiList;
	StringBuilder sb = new StringBuilder();
	Switch sw;
	static boolean switchStatus = false;
	AtomicBoolean ContinueThread = new AtomicBoolean(false);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sw = (Switch)findViewById(R.id.switch1);
		
		//Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
		
		mainText = (TextView) findViewById(R.id.textView2);
		  mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		  receiverWifi = new WifiReceiver();
		  registerReceiver(receiverWifi, new IntentFilter(
		  WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		  //mainWifi.startScan();
		  //mainText.setText("\nStarting Scan...\n");
		  
		  
		  
		  
		  sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
			if(isChecked) {
				switchStatus = true;
				new TurnWifiOn().execute("test");
				
			} else {
				switchStatus = false;
				
				Toast.makeText(getApplicationContext(),"Logging data Stopped", Toast.LENGTH_SHORT).show();
		  
				mainText.setText("Nothing to Display");
					
				
			}
				
				
				
			}
		});
		  			
	}
	
	
	
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			
			mainWifi.startScan();
			mainText.setText("Starting Scan...\n");
		}
		
		
	};
	
	
	
	
	

	public boolean onCreateOptionsMenu(Menu menu) {
		 menu.add(0, Menu.FIRST, Menu.NONE, "Refresh").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				mainWifi.startScan();
				  mainText.setText("Starting Scan");
				
				
				return true;
			}
		});
		 
		 
		 
		 menu.add(0, Menu.FIRST+1 , Menu.NONE, "Cell-Tower Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				startActivity(new Intent(MainActivity.this, CellInfo.class));
		
				
				return true;
			}
		});
		 
		 
		 menu.add(0,Menu.FIRST+2,Menu.NONE, "GPS-Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(MainActivity.this, GPSInfo.class));
				return true;
			}
		});
		 
		 
		 
		 
		 
		 menu.add(0,Menu.FIRST+3,Menu.NONE, "Check Data").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(MainActivity.this, DBCheck.class));
				return true;
			}
		});
		 return super.onCreateOptionsMenu(menu);
		 }
	
		
		
		 protected void onPause() {
		   unregisterReceiver(receiverWifi);
		   super.onPause();
		 }
		 
		 
		 
		 
		 class TurnWifiOn extends AsyncTask<String, String, String> {
			
			 
			 protected String doInBackground(String... param1){
				 
				 while(switchStatus){
					 
					 Thread runBackground = new Thread(new Runnable(){
					 
						@Override
						public void run(){
						 
						 handler.sendMessage(handler.obtainMessage());
					 }
				 });
					 
					 runBackground.start();
					 try{
						 Thread.sleep(300000);
					 }catch (InterruptedException e){
						 e.printStackTrace();
					 }
					 
					 
			 }
			 
			 return null;
		 }
}	 
	
		 
		 class WifiReceiver extends BroadcastReceiver {
			  public void onReceive(Context c, Intent intent) {
			  sb = new StringBuilder();
			 
			  
			  Calendar g = Calendar.getInstance();
			  Date date = g.getTime();
			  
			  wifiList = mainWifi.getScanResults();
			  int sizenum = wifiList.size();
			  Toast.makeText(getApplicationContext(),"WifiInfo:Wifi Networks Found:" + sizenum, Toast.LENGTH_SHORT).show();
			  
			      
		    	 
			  for(int i = 0; i < wifiList.size(); i++){
			     sb.append(new Integer(i+1).toString() + ".");
			     sb.append("SSID:"+(wifiList.get(i)).SSID.toString());
			     sb.append("\nBSSID:"+(wifiList.get(i)).BSSID.toString());
			     sb.append("\nSignal Strength:"+(wifiList.get(i)).level+"dBm");
			     sb.append(("\nTimestamp in millis:"+(wifiList.get(i)).timestamp));
			     sb.append("\nClock when fetched:"+date);
			     sb.append("\n");
			     sb.append("\n");
			    //doWifiapData((wifiList.get(i)).SSID.toString(), (wifiList.get(i)).level,((wifiList.get(i)).timestamp),date );
			          
			    // dbHelper = OpenHelperManager.getHelper().(this, DatabaseHelper.class);
			    
			     
			     RuntimeExceptionDao<Wifiap,Integer>WifiapDao=getHelper().getWifiapRuntimeExceptionDao();
			     
			     
			    String SSID = (wifiList.get(i)).SSID.toString();
			     int Level = (wifiList.get(i)).level;
			     long Timestamp = ((wifiList.get(i)).timestamp);
			     String BSSID = ((wifiList.get(i)).BSSID.toString());
			    
			     //System.out.println("SSID:"+SSID+"Level:"+Level+"Timestamp:"+Timestamp);
			     
			         
			     
			     Wifiap wfp = new Wifiap(SSID,BSSID,Level,Timestamp);
			       
			     WifiapDao.createIfNotExists(wfp);
			        
			     		     
				/*
				 List<Wifiap> Wifiaps = (WifiapDao.queryForEq("id", 1));
 		    	 Log.d("demo", Wifiaps.toString());
 		    	 
 		    	 DBDisplay dbDisplay = new DBDisplay();
 		    	 dbDisplay.sendList(Wifiaps);
 		    	 
			     
			    */ 
			     
			  }
			mainText.setText(sb);
			
			
			}
		 }

}
