package com.tk.wifitest;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class CellInfodb {
	
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private int cellid;
	
	@DatabaseField
	private int lac;
	
	@DatabaseField	
	private int mcc;
	
	@DatabaseField
	private int mnc;
	
	@DatabaseField
	private java.util.Date ClockC;
	
	
	public CellInfodb(){}


	public CellInfodb(int cellid, int lac, int mcc, int mnc) {
		super();
		this.cellid = cellid;
		this.lac = lac;
		this.mcc = mcc;
		this.mnc = mnc;
		this.ClockC = new Date(System.currentTimeMillis());
		
	}


	@Override
	public String toString() {
		return "\n" + id +"."+ "cellid=" + cellid + "\nlac=" + lac
				+ "\nmcc=" + mcc + "\nmnc=" + mnc + "\nClock=" + ClockC + "\n";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
