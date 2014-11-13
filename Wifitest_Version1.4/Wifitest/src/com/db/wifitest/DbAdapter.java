package com.db.wifitest;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {
	/*Declare fields required to be saved in database, declare, table and database*/
	public static final String SSID="ssid";
	public static final String SIGNAL_STRENGTH="level";
	public static final String TIMESTAMP="timestamp";
	public static final String CLOCK_FETCHED="clockfetched";
	public static final String TABLE_NAME="locationtable";
	public static final String DATABASE_NAME="locationdatabase";
	public static final int DATABASE_VERSION=1;
	
	/*Create table*/
	public static final String TABLE_CREATE="create table locationtable" +
			"(ssid text not null,level text not null,timestamp text not null" +
			",clockfetched text not null);";
	
	/*Object creation for inner class*/
	DataBaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;
	
	/*Constructor for the class*/
	public DbAdapter(Context ctx)
	{
		this.ctx=ctx;	
		dbhelper=new DataBaseHelper(ctx);
	}
	
	/*Create database helper class*/
	private static class DataBaseHelper extends SQLiteOpenHelper
	{
		/*Constructor for databasehelper class*/
		public DataBaseHelper(Context ctx)
		{
			super(ctx,DATABASE_NAME,null,DATABASE_VERSION);//one of the method of the class SQLiteOpenHelper
		}
		
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			/*For creating table in the database*/
			try{
			db.execSQL(TABLE_CREATE);
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}		
		
		@Override
		public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
		{
			/*Drop the database table if already exists and create again*/
			db.execSQL("DROP TABLE IF EXISTS locationtable");
			onCreate(db);
			
		}		
		
	}
	
	/*Open the database outside the inner class to insert values*/
	public DbAdapter open()
	{
		db=dbhelper.getWritableDatabase();
		return this;
	}
	
	/*Method to close the database*/
	public void close()
	{
		dbhelper.close();
	}
	
	/*Method for inserting data into the table*/
	public long insertData(String ssid,String level, String timestamp,String clockfetched)
	{
		ContentValues content=new ContentValues();
		content.put(SSID,ssid);
		content.put(SIGNAL_STRENGTH, level);
		content.put(TIMESTAMP,timestamp);
		content.put(CLOCK_FETCHED,clockfetched);
		return db.insertOrThrow(TABLE_NAME, null, content);//adds the data into database
	}
	
	/*Retrieve the value of the data, here the return type is cursor*/
	public Cursor returnData()
	{
		return db.query(TABLE_NAME, new String[]{SSID,SIGNAL_STRENGTH,TIMESTAMP,CLOCK_FETCHED}
		, null, null, null, null, null);
	}

}

	
	
	
	
	
	
	
	
	
	
	
	

	
	
