package fr.excilys.cdb.view.menu;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import fr.excilys.cdb.service.CompanyService;
import fr.excilys.cdb.view.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntryVerification {
	
	@Autowired
	private static CompanyService companyService;

	public static String readString() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public static int readInteger(String message) {
		int returnValue = -1;
		boolean validValue = false;
		
		do {
			try {
				returnValue = Integer.valueOf(readString());
				validValue = true;
			}catch(NumberFormatException e) {
				System.out.println(message);
			}
		}while(!validValue);
		
		return returnValue;
	}
	
	public static String readComputerName() {
		System.out.println("What's the computer name ?:");
		String name = null;
		
		do {
			name = EntryVerification.readString();
			
			if(name == "") {
				View.logger.error("the company name should not null");
			}
		}while(name == "");
		return name;
	}
	
	public static LocalDate readDate(boolean nullable) {
		String dateString = null;
		LocalDate date = null;
		
		do {
			
			dateString = EntryVerification.readString();
			
			if(nullable && dateString.equals("")) {
				Optional.of(dateString);
			}else if(!EntryVerification.isValidDate(dateString)){
				View.logger.error("The date format is not valid");
			}else {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date = LocalDate.parse(dateString, formatter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}while(!(EntryVerification.isValidDate(dateString) || (nullable && dateString != null)));
		
		return date;
	}	
	
	private static boolean isValidDate(String date) {
		
		if(!date.equals("")) {
			try {
				LocalDate vDate;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				vDate = LocalDate.parse(date, formatter);
			}catch(IllegalArgumentException e) {
				return false;
			}
		}		
		return true;
	}
	
	public static int readCompanyId(){
		System.out.println("What's the company id ?");
		int companyId = -1;
		
//		do {
//			companyId = EntryVerification.readInteger("The company id should be an integer");
//			
//			if(!companyService.existentById(companyId)){
//				View.logger.error("The company does not exist\n");
//			}
//		}while(!companyService.existentById(companyId));
		
		return companyId;
	}
}
