package fr.excilys.cdb.test.DAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.persistance.ComputerDAO;
import fr.excilys.cdb.persistance.DAO;

public class ComputerDaoTest {
	
	private static String DB_CONF = "jdbc:mysql://localhost:3306/computer-database-dbtest";
	private static String DB_USER = "admincdb";
	private static String DB_PASS = "qwerty1234";
	private static Connection connection;
	private static List<Computer> computers = new ArrayList<Computer>();
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
	public void testGetAllComputer() {
		ComputerDAO test = new ComputerDAO();
		
		computers.add(new Computer(1,"atari",in,out,6));
		computers.add(new Computer(2,"Windows 98",in,out,4));
		computers.add(new Computer(3,"Mac-S65",in,out,2));
		for(Computer element : computers) {
			test.add(element);
		}
		//ComputerDAO test = new ComputerDAO();
		List<Computer> getComputer = new ArrayList<Computer>();
		getComputer = (List<Computer>) test.getAll();
		
		int i = 0;
				
		System.out.println("======\n");
		
		for(Computer element : getComputer) {

			assertTrue(computers.contains(element));
			System.out.println("assertTrue getAll() >> OK");
			assertEquals(computers.get(i++),element);
			System.out.println("assertEquals getAll() >> OK");
		}
		
		for(Computer element : computers) {
			test.deleteById(element.getId());
			if(!test.existentById(element.getId())) {
				System.out.println("Computer correctly deleted in getAll() test");
			}
		}
		System.out.println("======\n");
	}
	
	@Test
	public void testAddComputer() {
		ComputerDAO test = new ComputerDAO();
		Computer computer = new Computer(1,"atari",in,out,6);
		
		Optional<Computer> addedComputer = test.add(computer);
		Optional<Computer> newComputer = test.getId(addedComputer.get().getId());
		
		System.out.println("======\n");
		if(addedComputer.isPresent()) {			
			assertTrue(test.existentById(addedComputer.get().getId()));
			System.out.println("assertTrue add() >> OK");
			assertEquals(addedComputer.get(),newComputer.get());
			System.out.println("assertEquals add() >> OK");
			
			test.deleteById(newComputer.get().getId());
			if(!test.existentById(addedComputer.get().getId())) {
				System.out.println("Computer correctly deleted in add() test");
			}
		}
		System.out.println("======\n");
	}
	
	@Test
	public void testUpdateComputer() {
		ComputerDAO test = new ComputerDAO();
		Computer computer = new Computer(1,"atari",in,out,6);
		
		Optional<Computer> addComputer = test.add(computer);
		Optional<Computer> addedComputer = test.getId(addComputer.get().getId());
		
		Optional<Computer> updateComputer = addedComputer;
		updateComputer.get().setName("MacIntosh");
		test.update(updateComputer.get());
		Optional<Computer> updatedComputer = test.getId(updateComputer.get().getId());
		
		System.out.println("======\n");
		if(updatedComputer.isPresent()) {
			assertEquals(addedComputer.get().getId(),updatedComputer.get().getId());
			System.out.println("assertEquals on id update() >> OK");
			assertTrue(addedComputer.get().getName() != updatedComputer.get().getName());
			System.out.println("assertTrue on name update() >> OK");
		}
		
		test.deleteById(updatedComputer.get().getId());
		if(!test.existentById(updatedComputer.get().getId())) {
			System.out.println("Computer correctly deleted in update()");
		}
		System.out.println("======\n");
	}
	
	@Test
	public void testDeleteComputerById(){
		ComputerDAO test = new ComputerDAO();
		Computer computer = new Computer(1,"atari",in,out,6);
		
		Optional<Computer> addComputer = test.add(computer);		
		Optional<Computer> addedComputer = test.getId(addComputer.get().getId());
		
		System.out.println("======\n");
		if(addedComputer.isPresent()) {
			try {
			test.deleteById(addedComputer.get().getId());
			assertFalse(test.existentById(addedComputer.get().getId()));
			System.out.println("assertFalse on delete() >> OK");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("======\n");		
	}
}
