package com.tk.wifitest;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class GPSInfodb {

	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String Latitude;
	
	
	@DatabaseField
	private String Longitude;
	
	
	@DatabaseField
	private String Timestamp;
	
	@DatabaseField
	private java.util.Date ClockG;
	
	
	public GPSInfodb(){}


	public GPSInfodb(String latitude, String longitude, String timestamp) {
		super();
		this.Latitude = latitude;
		this.Longitude = longitude;
		this.Timestamp = timestamp;
		this.ClockG = new Date(System.currentTimeMillis());
		
	}
	@Override
	public String toString() {
		return ("\n" + id + "." +"Latitude=" + Latitude
				+ "\nLongitude=" + Longitude + "\nTimestamp=" + Timestamp
				+ "\nClock=" + ClockG + "\n");
	}
	
}
