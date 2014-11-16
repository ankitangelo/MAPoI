/* Author: Shreyas Srinivasa */

package com.tk.wifitest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
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

public class CellInfo extends OrmLiteBaseActivity<DatabaseHelper>  {
	
	
	static boolean switchStatus=false;
	
	AtomicBoolean ContinueThread = new AtomicBoolean(false);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cell_info);
		
		Switch sw;
		
		sw = (Switch)findViewById(R.id.switch1);
		sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if(isChecked){
					switchStatus = true;
					new TurnCellInfoOn().execute("test");
				} else {
					
					switchStatus = false;
					
					Toast.makeText(getApplicationContext(), "Logging Data Stopped", Toast.LENGTH_SHORT).show();
					
					
				}
				
				
				
				
				
			}
		});
		
		
	}	
		Handler handler = new Handler(){
			
			@Override
			public void handleMessage (Message msg){
				fetchinfo();
			}
		
		
	};
        
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
				
				
				RuntimeExceptionDao<CellInfodb,Integer>CellDao=getHelper().CellInfodbRuntimeExceptionDao();
				
				  	 
				 CellInfodb cif = new CellInfodb(cid,lac,mcc,mnc);
				 
				 CellDao.createIfNotExists(cif);
				
				 
				 
				 List<CellInfodb> Cellps = (CellDao.queryForAll());
 		    	 Log.d("cellinfo", Cellps.toString());
				 
					
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
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	class TurnCellInfoOn extends AsyncTask<String, String, String> {
		
		
		protected String doInBackground(String... param1) {
			
			while(switchStatus) {
				
				
				Thread runBackground = new Thread (new Runnable() {
					
					
					@Override
					public void run() {
						
						handler.sendMessage(handler.obtainMessage());
					}
				});
				
				runBackground.start();
				try{
					Thread.sleep(300000);
				}catch(InterruptedException e){
					
					e.printStackTrace();
				}
				
			}
			return null;
					
				}
			}
				
			
		}
		
		

