package fr.excilys.cdb.test.DAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.persistance.DAO;

public class CompanyDaoTest {
	private static String DB_CONF = "jdbc:mysql://localhost:3306/computer-database-dbtest";
	private static String DB_USER = "admincdb";
	private static String DB_PASS = "qwerty1234";
	private static String COUNT_QUERY = "SELECT COUNT(id) AS count FROM company";
	private static Connection connection;

	@BeforeClass
	public static void initialise() throws SQLException {
		DAO.setDB_CONF(DB_CONF);
		DAO.setDB_PASS(DB_PASS);
		DAO.setDB_USER(DB_USER);
		connection = DAO.getConnection();
	}
	
	@Test
	public void testGetAll() throws SQLException{
		CompanyDAO test = new CompanyDAO();
		Collection<Company> companies = new ArrayList<Company>();
		int count = 0;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet  = statement.executeQuery(COUNT_QUERY);
			
			while(resultSet.next()) {
				count = resultSet.getInt("count");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("======");
		if(count != 0) {
			companies = test.getAll();
			assertTrue(companies != null);
			System.out.println("asserTrue in getAll() >> OK");
			assertEquals(companies.size(),count);
			System.out.println("assertEquals in getAll() >> OK");
		}
		System.out.println("======");
	}
}
