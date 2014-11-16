package com.tk.wifitest;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil{
  
	private static final Class<?>[] classes = new Class[] {Wifiap.class};
	
	private static final Class<?>[] classesC = new Class[] {CellInfodb.class};
	
	private static final Class<?>[] classesG = new Class[] {GPSInfodb.class};

	public static void main(String[] args) throws SQLException, IOException {
		writeConfigFile("ormlite_config.txt",classes);
		writeConfigFile("ormlite_config.txt",classesC);
		writeConfigFile("ormlite_config.txt",classesG);

	}
}
	
		
	
	
	
	
	
	
	
	

