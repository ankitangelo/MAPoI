package com.tk.wifitest;

import java.util.List;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class DBCheck extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbcheck);
		
		final RadioGroup rg;
		
		final TextView dbdisplay;
		
		dbdisplay = (TextView)findViewById(R.id.textView3);
		
		rg = (RadioGroup) findViewById(R.id.rgroup);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton r1 = (RadioButton) findViewById(R.id.radioButton1);
				RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
				RadioButton r3 = (RadioButton) findViewById(R.id.radioButton3);
				
				if(r1.isChecked())
				{
					RuntimeExceptionDao<Wifiap,Integer>WifiapDao=getHelper().getWifiapRuntimeExceptionDao();
					List<Wifiap> Wifiaps = (WifiapDao.queryForAll());
			    	dbdisplay.setText(Wifiaps.toString());
				}
				else if(r2.isChecked())
				{
					RuntimeExceptionDao<CellInfodb,Integer>CellDao=getHelper().CellInfodbRuntimeExceptionDao();
			    	List<CellInfodb> Cellps = (CellDao.queryForAll());
			    	dbdisplay.setText(Cellps.toString());
			    	
				}
				else if (r3.isChecked())
				{
					RuntimeExceptionDao<GPSInfodb,Integer>GPSDao=getHelper().GPSInfodbRuntimeExceptionDao();
					List<GPSInfodb> gpsps = (GPSDao.queryForAll());
					dbdisplay.setText(gpsps.toString());
				}
				
			}
		});
		
				
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 menu.add(0, Menu.FIRST, Menu.NONE, "Wifi AP Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(DBCheck.this, MainActivity.class));
				return true;
			}
		});
		 
		 
			 
		 menu.add(0, Menu.FIRST+1 , Menu.NONE, "Cell-Tower Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(DBCheck.this, CellInfo.class));
				return true;
			}
		});
		 
		 
		 		 
		 menu.add(0,Menu.FIRST+2,Menu.NONE, "GPS-Info").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				startActivity(new Intent(DBCheck.this, GPSInfo.class));
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
}
