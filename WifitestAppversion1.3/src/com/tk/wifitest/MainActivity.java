
/* Author: Shreyas Srinivasa */


package com.tk.wifitest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;



public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {
	
	private final String LOG_TAG = getClass().getSimpleName();
	
	DatabaseHelper dbHelper;
	
	TextView mainText;
	WifiManager mainWifi;
	WifiReceiver receiverWifi;
	List<ScanResult> wifiList;
	StringBuilder sb = new StringBuilder();
	//StringBuilder wp = new StringBuilder();
	//TextView wpText;
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
		
		mainText = (TextView) findViewById(R.id.textView2);
		  mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		  receiverWifi = new WifiReceiver();
		  registerReceiver(receiverWifi, new IntentFilter(
		  WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		  mainWifi.startScan();
		  mainText.setText("\nStarting Scan...\n");
		  
				
	}

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
			    
			    System.out.println("SSID:"+SSID+"Level:"+Level+"Timestamp:"+Timestamp);
			     
			    // Wifiap wfp = new Wifiap(SSID,level,Timestamp);
			     
			     
			     Wifiap wfp = new Wifiap(SSID,Level,Timestamp);
			       
			     WifiapDao.createIfNotExists(wfp);
			     Log.i(LOG_TAG, "created simple(" + SSID + ")");
			     
			     
			     //WifiapDao.create(new Wifiap((wifiList.get(i)).SSID.toString(),(wifiList.get(i)).level,(wifiList.get(i)).timestamp));
			     
			    // wfp.SSID = (wifiList.get(i)).SSID.toString();
				// wfp.Level = (wifiList.get(i)).level ;
				// wfp.Timestamp = (wifiList.get(i)).timestamp;
				
				 List<Wifiap> Wifiaps = (WifiapDao.queryForEq("id", 1));
 		    	 Log.d("demo", Wifiaps.toString());
			     
			     
			     
			  }
			mainText.setText(sb);
			
			}
		 }
		 
		 
		// private void doWifiapData(String SSID1,int level1,long timestamp1,Date date1){
			 
		//Log.e("Cought You1","its here");	 
		
		//dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
    	 
		// Log.e("Cought You2","its here");
		 
		
		
		//RuntimeExceptionDao<Wifiap,Integer>WifiapDao=dbHelper.getWifiapRuntimeExceptionDao();
    	 
		 
		// Wifiap wfp = new Wifiap();
		 
//		 wfp.SSID = SSID1;
//		 wfp.Level = level1;
//		 
//		 
//		 
//		 WifiapDao.create(wfp);
//    	 
//    	 List<Wifiap> Wifiaps = (WifiapDao.queryForAll());
//    	 Log.d("demo", Wifiaps.toString());
    	 
    	//WifiapDao.create(new Wifiap(SSID1.toString(), String.valueOf(level1), String.valueOf(timestamp1), date1));
    	
    	 //Log.e("Cought You3","its here");
    	 
    	 
    	//wp.append(WifiapDao.queryForAll());
    	//wpText = (TextView) findViewById(R.id.textView3);
    	
    	//wpText.setText(wp);
    	
    				
		// }	
	     




}
