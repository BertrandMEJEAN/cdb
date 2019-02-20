package fr.excilys.cdb.persistance;

import java.sql.*;

public class DAO{
	
	private static Connection connection;
	
	private static final String DB_CONF = "jdbc:mysql://localhost:3306/computer-database-db";
	private static final String DB_USER = "admincdb";
	private static final String DB_PASS = "qwerty1234";
	
	/**
	 * Singleton pour Instancier ou récupérer l'objet de connection à la base de donnée.
	 * @return Retourne l'objet connection initialisant la connection à la base de donnée.
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		if(connection == null) {
			connection = DriverManager.getConnection(DB_CONF,DB_USER,DB_PASS);
		}			
		return connection;
	}

}
