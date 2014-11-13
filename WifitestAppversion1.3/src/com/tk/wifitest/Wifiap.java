package com.tk.wifitest;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Wifiap {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String SSID;
	
	@DatabaseField
	private int Level;
	
	@DatabaseField
	private Long Timestamp;
	
	@DatabaseField
	private java.util.Date Clock;
	
	
	
	
public Wifiap(){}	
	
	
	



	public Wifiap(String SSID, int Level, Long Timestamp) {
		super();
		this.SSID = SSID;
		this.Level = (Level);
		this.Timestamp = (Timestamp);
		this.Clock = new Date(System.currentTimeMillis());
	}

	

	@Override
	public String toString() {
		return "Wifiap [id=" + id + ", SSID=" + SSID + ", Level=" + Level
				+ ", Timestamp=" + Timestamp + ", Clock=" + Clock + "]";
	}

	
	
	
	
	
	


//	@Override
//	public String toString() {
//		StringBuilder wb = new StringBuilder();
//		wb.append("id=").append(id);
//		wb.append(", ").append("SSID=").append(SSID);
//		wb.append(", ").append("Level=").append(Level);
//		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.S");
//		wb.append(", ").append("date=").append(dateFormatter.format(Clock));
//		return wb.toString();
//	}






	

	
	
}
