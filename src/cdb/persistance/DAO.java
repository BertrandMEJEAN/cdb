package cdb.persistance;

import java.sql.*;

public class DAO{
	
	private static Connection connection;
	
	private static final String DB_CONF = "jdbc:mysql://localhost:3306/computer-database-db";
	private static final String DB_USER = "admincdb";
	private static final String DB_PASS = "qwerty1234";
	
	public static Connection getConnection() throws SQLException{
		if(connection == null) {
			connection = DriverManager.getConnection(DB_CONF,DB_USER,DB_PASS);
		}			
		return connection;
	}

}
