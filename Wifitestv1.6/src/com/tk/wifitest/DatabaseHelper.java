package com.tk.wifitest;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
	
	
	private static final String DATABASE_NAME = "wifi.db";
	private static final int DATABASE_VERSION = 1;
	
	
	private Dao<Wifiap, Integer> WifiapDao=null;
	private Dao<CellInfodb, Integer> CellDao=null;
	private Dao<GPSInfodb, Integer> GPSDao=null;
	
	
	
	
	private RuntimeExceptionDao<Wifiap, Integer> WifiapRuntimeDao=null;
	private RuntimeExceptionDao<CellInfodb, Integer> CellInfodbRuntimeDao=null;
	private RuntimeExceptionDao<GPSInfodb, Integer> GPSInfodbRuntimeDao=null;
	
	
	
	
	
	
	
	
	
	
	public DatabaseHelper(Context context){
		
		super(context,DATABASE_NAME,null,DATABASE_VERSION,R.raw.ormlite_config);
	}


	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		
		
		
		try {
			TableUtils.createTable(connectionSource, Wifiap.class);
			TableUtils.createTable(connectionSource, CellInfodb.class);
			TableUtils.createTable(connectionSource, GPSInfodb.class);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		
		try {
			TableUtils.dropTable(connectionSource, Wifiap.class, true);
			TableUtils.dropTable(connectionSource, CellInfodb.class, true);
			TableUtils.dropTable(connectionSource, GPSInfodb.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public Dao<Wifiap, Integer> getDao() throws SQLException{
		
		if(WifiapDao==null) {
			WifiapDao= getDao(Wifiap.class);
			
		}
		return WifiapDao;
		
	}
	
	
	
   public Dao<CellInfodb, Integer> getCDao() throws SQLException{
		
		if(CellDao==null) {
			CellDao= getDao(CellInfodb.class);
			
		}
		return CellDao;
		
	}
	
	
   
   public Dao<GPSInfodb, Integer> getGDao() throws SQLException{
		
		if(GPSDao==null) {
			GPSDao= getDao(CellInfodb.class);
			
		}
		return GPSDao;
		
	}
	
	
	
	
	public RuntimeExceptionDao<Wifiap, Integer>getWifiapRuntimeExceptionDao(){
	
		if(WifiapRuntimeDao==null){
			WifiapRuntimeDao = getRuntimeExceptionDao(Wifiap.class);
		}
		
		return WifiapRuntimeDao;
		
			
		}
	
	
	public RuntimeExceptionDao<CellInfodb, Integer>CellInfodbRuntimeExceptionDao(){
		
		if(CellInfodbRuntimeDao==null){
			CellInfodbRuntimeDao = getRuntimeExceptionDao(CellInfodb.class);
		}
		
		return CellInfodbRuntimeDao;
		
			
		}
	
	
public RuntimeExceptionDao<GPSInfodb, Integer>GPSInfodbRuntimeExceptionDao(){
		
		if(GPSInfodbRuntimeDao==null){
			GPSInfodbRuntimeDao = getRuntimeExceptionDao(GPSInfodb.class);
		}
		
		return GPSInfodbRuntimeDao;
		
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

