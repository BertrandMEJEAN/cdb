package fr.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;


@Repository
public class DAO{
	
	private HikariDataSource hikariSource;
	
	public DAO(HikariDataSource hikariSource) {
		this.hikariSource = hikariSource;
	}
	
	/**
	 * Singleton pour Instancier ou récupérer l'objet de connection à la base de donnée.
	 * @return Retourne l'objet connection initialisant la connection à la base de donnée.
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return hikariSource.getConnection();
	}
}
