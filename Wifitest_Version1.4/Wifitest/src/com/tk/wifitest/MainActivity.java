
/* Author: Shreyas Srinivasa */
/*Addition of Threads by Pooja and Nandita*/ 

package com.tk.wifitest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView mainText;
	WifiManager mainWifi;
	WifiReceiver receiverWifi;
	List<ScanResult> wifiList;
	StringBuilder sb = new StringBuilder();
	Switch sw;
	AtomicBoolean ContinueThread=new AtomicBoolean(false);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sw = (Switch) findViewById(R.id.mySwitch);
		
		mainText = (TextView) findViewById(R.id.textView2);
		
		mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		  receiverWifi = new WifiReceiver();
		  registerReceiver(receiverWifi, new IntentFilter(
		  WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		  
		  sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	@Override
	      public void onCheckedChanged(CompoundButton buttonView,boolean ischecked) {
	                if (ischecked) {
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
	                    
	                } 
	                
	                //Stop the running of the thread
	                else {
	                	
	      			  //ContinueThread.set(false);	                	
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
	                    runBackground.interrupt();
	                    runBackground=null;
	                	
	                    Toast.makeText(getApplicationContext(), "Logging data stopped",Toast.LENGTH_LONG).show();
	                     Log.e("Mango","Hi");   
	                     mainText.setText("Nothing to display");
	                    
	                }
	            }
	        });
		  } 
		  
		  
		  //mainWifi.startScan();
		  //mainText.setText("\nStarting Scan...\n");  				
		  //new Thread(new ThreadRunnable()).start();

	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg){
			  mainWifi.startScan();
			  mainText.setText("\nStarting Scan...\n"); 
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
			}
			mainText.setText(sb);
			
			}
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
		  
		  /*public void onStop(){
			  super.onStop();
			  ContinueThread.set(false);
		  }*/
		  /*public class ThreadRunnable implements Runnable
		  {
			  @Override
			  public void run(){
				  mainWifi.startScan();
				  mainText.setText("\nStarting Scan...\n");  
				  try {
					
					Toast.makeText(getApplicationContext(), "Thread sleeping",Toast.LENGTH_SHORT).show();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		  }*/
}
