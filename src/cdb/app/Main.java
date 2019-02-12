package cdb.app;

import java.sql.SQLException;
import java.util.Scanner;

import cdb.model.Computer;
import cdb.persistance.*;

public class Main {

	public static void main(String[] args) throws SQLException{
		
		int choice;
		
		DAO.getConnection();
		
		System.out.println("Veuillez faire votre choix:\n"
				+ "1.Lister les ordinateurs\n"
				+ "2.Lister les entreprises\n"
				+ "3.Details d'un ordinateur\n"
				+ "4.Opération sur un ordinateur\n"
				+ "5.Sortir");
		
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		switch (str) {
				case "1": 
					ComputerDAO allCpt = new ComputerDAO();
					for(Computer computer : allCpt.getAll()){
						System.out.println(computer.toString());
					}					
					break;
				case "2": 
					CompanyDAO allCpy = new CompanyDAO();
					for(Company company: allCpy.)
					break;
				case "3": 
					System.out.println("choix 3");				
					break;
				case "4": 
					System.out.println("choix 4");				
					break;
				case "5": 
					System.out.println("choix 5");				
					break;
				default :
					System.out.println("Selectionnez entre 1 & 5");					
					break;
			}
			
	}

}
