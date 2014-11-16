package com.tk.wifitest;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Wifiap {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String SSID;
	
	@DatabaseField 
	private String BSSID;
	
	@DatabaseField
	private int Level;
	
	@DatabaseField
	private Long Timestamp;
	
	@DatabaseField
	private java.util.Date Clock;
	
	

public Wifiap(){}	

	public Wifiap(String SSID,String BSSID, int Level, Long Timestamp) {
		super();
		this.SSID = SSID;
		this.BSSID = BSSID;
		this.Level = (Level);
		this.Timestamp = (Timestamp);
		this.Clock = new Date(System.currentTimeMillis());
	}

	
	@Override
	public String toString() {
		return "\n " + id +"."+ "SSID=" + SSID + "\n     BSSID:"+BSSID+"\n    Signal Strength=" + Level+"dbm"
				+ "\n    Timestamp=" + Timestamp + "\n    Clock=" + Clock+"\n";
	}	
	


}


