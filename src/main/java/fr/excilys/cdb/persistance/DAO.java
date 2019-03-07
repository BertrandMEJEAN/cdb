package fr.excilys.cdb.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DAO{
	
	private static Connection connection;
	
	private static String DB_CONF;
	private static String DB_USER;
	private static String DB_PASS;
	private static String DRIVER;
	private static HikariDataSource hikari;
	
	/**
	 * Singleton pour Instancier ou récupérer l'objet de connection à la base de donnée.
	 * @return Retourne l'objet connection initialisant la connection à la base de donnée.
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		if(connection == null || connection.isClosed()) {
			
			try {
				loadProperties();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			if(hikari == null) {
				setUpHikari();
			}
		}			
		return connection = hikari.getConnection();
	}
	
	private static void loadProperties() throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(System.getProperty("user.home")+"/project/cdb/ressources/config.properties");
			
			prop.load(input);
			
			setDRIVER(prop.getProperty("dbdriver"));
			setDB_CONF(prop.getProperty("dbconf"));
			setDB_USER(prop.getProperty("dbuser"));
			setDB_PASS(prop.getProperty("dbpassword"));
			
		}catch(IOException io) {
			io.printStackTrace();
		}finally {
			if(input != null) {
				try {
					input.close();
				}catch(IOException io) {
					io.printStackTrace();
				}
			}
		}
	}
	
	private static void setUpHikari() {
		HikariConfig conf = new HikariConfig();
		conf.setDriverClassName(DRIVER);
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

	public static String getDRIVER() {
		return DRIVER;
	}
	
	public static void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}
}
