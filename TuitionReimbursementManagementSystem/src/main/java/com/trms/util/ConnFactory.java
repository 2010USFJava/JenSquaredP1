package com.trms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Singleton Factory
	//private static instance of itself
	private static ConnFactory cf;
		
	//private no-args constructor
	private ConnFactory() {
		super();
	}
		
	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
		}
		return cf;
	}
		
	//methods that do stuff
		public Connection getConnection() {
			Connection conn = null;
			Properties prop = new Properties();

			try {
				String url = "jdbc:postgresql://java2010rev.cfqzgdfohgof.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=jensquared";
				String username = "jenny77";
				String password = "zeus1418";
//				ClassLoader loader = Thread.currentThread().getContextClassLoader();
//	            prop.load(loader.getResourceAsStream("database.properties"));
//				conn = DriverManager.getConnection(prop.getProperty("url"),
//						prop.getProperty("username"),prop.getProperty("password"));
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			return conn;
		}	

}