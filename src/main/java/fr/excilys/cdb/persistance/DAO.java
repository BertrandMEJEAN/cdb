package fr.excilys.cdb.persistance;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;


@Repository
public class DAO{
	
	@Autowired
	private HikariDataSource hikariSource;
	
	/**
	 * Singleton pour Instancier ou récupérer l'objet de connection à la base de donnée.
	 * @return Retourne l'objet connection initialisant la connection à la base de donnée.
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return hikariSource.getConnection();
	}
}
