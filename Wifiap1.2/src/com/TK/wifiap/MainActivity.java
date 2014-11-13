package com.TK.wifiap;

/* author Shreyas Srinivasa */

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;




public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getwifiap();
		
	}

	private void getwifiap() {
		
		

		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> apList = wifiManager.getScanResults();
        
        String[] wifilist = (String[]) apList.toArray();
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.wifilist, wifilist);
        
        ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(adapter);
        
	}

	
		
		
	}

	

