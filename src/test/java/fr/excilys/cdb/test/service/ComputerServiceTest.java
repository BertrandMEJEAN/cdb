package fr.excilys.cdb.test.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.ComputerDetails;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.persistance.DAO;

public class ComputerServiceTest {
	private static String DB_CONF = "jdbc:mysql://localhost:3306/computer-database-dbtest";
	private static String DB_USER = "admincdb";
	private static String DB_PASS = "qwerty1234";
	private static Connection connection;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate in = LocalDate.parse("12-09-2019", formatter);
	LocalDate out = LocalDate.parse("21-10-2020", formatter);

	@BeforeClass
	public static void initialise() throws SQLException {
		DAO.setDB_CONF(DB_CONF);
		DAO.setDB_PASS(DB_PASS);
		DAO.setDB_USER(DB_USER);
		connection = DAO.getConnection();
	}
	
	@Test
	public void testComputersDetails(){
		ComputerService test = ComputerService.getInstance();
		CompanyDAO company = CompanyDAO.getInstance();
		ComputerDetails allDetails = null;
		Company cpyDetails = null;
		Computer cptDetails = null;
		Computer addCompter = new Computer(1,"atari",in,out,company.getId(1));
		
		Optional<Computer> addedComputer = test.add(addCompter);
		
		System.out.println("======");
		if(addedComputer.isPresent()) {
			allDetails = test.getDetailsByComputerId(addedComputer.get().getId());
			cpyDetails = allDetails.getCompany();
			cptDetails = allDetails.getComputer();
			assertTrue(allDetails != null);
			System.out.println("assertTrue computerDetails() >> OK");
			assertEquals(cpyDetails.getId(),cptDetails.getCompany().getId());
			System.out.println("asserEquals computerDetails() >> OK");
			test.deleteById(cptDetails.getId());
			System.out.println("Computer correctly deleted");			
		}
		System.out.println("======");
	}
}
