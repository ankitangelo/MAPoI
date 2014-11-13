package com.tk.wifitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DBCheck extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbcheck);
		
		Button fetch;
		
		fetch = (Button)findViewById(R.id.button1);
		
		fetch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				startActivity(new Intent(DBCheck.this, DBDisplay.class));
				
				
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
