package fr.excilys.cdb.persistance;

import java.sql.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DAO{
	
	private static Connection connection;
	
	private static String DB_CONF = "jdbc:mysql://localhost:3306/computer-database-db";
	private static String DB_USER = "admincdb";
	private static String DB_PASS = "qwerty1234";
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static HikariDataSource hikari;
	
	/**
	 * Singleton pour Instancier ou récupérer l'objet de connection à la base de donnée.
	 * @return Retourne l'objet connection initialisant la connection à la base de donnée.
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		if(connection == null || connection.isClosed()) {
			
			try {
	            Class.forName(DRIVER);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
			
			if(hikari == null) {
				setUpHikari();
			}
		}			
		return connection = hikari.getConnection();
	}
	
	private static void setUpHikari() {
		HikariConfig conf = new HikariConfig();
		conf.setJdbcUrl(DB_CONF);
		conf.setUsername(DB_USER);
		conf.setPassword(DB_PASS);
		conf.setConnectionTimeout(10000L);
		hikari = new HikariDataSource(conf);
	}
	
	public void closePool() {
		if(hikari != null) {
			hikari.close();
		}
	}

	public static String getDB_CONF() {
		return DB_CONF;
	}

	public static void setDB_CONF(String dB_CONF) {
		DB_CONF = dB_CONF;
	}

	public static String getDB_USER() {
		return DB_USER;
	}

	public static void setDB_USER(String dB_USER) {
		DB_USER = dB_USER;
	}

	public static String getDB_PASS() {
		return DB_PASS;
	}

	public static void setDB_PASS(String dB_PASS) {
		DB_PASS = dB_PASS;
	}
}
