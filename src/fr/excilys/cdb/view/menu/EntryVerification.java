package fr.excilys.cdb.view.menu;

import java.util.Scanner;

import fr.excilys.cdb.service.CompanyService;

import java.sql.Timestamp;

public class EntryVerification {

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
				//Logger.error("the company name should not null");
			}
		}while(name == "");
		return name;
	}
	
	public static Timestamp readDate(boolean nullable) {
		String dateString = null;
		Timestamp date = null;
		
		do {
			
			if(nullable && dateString.equals("")) {
				date = null;
			}else if(!EntryVerification.isValidDate(dateString)){
				//logger.error("The date format is not valid");
			}else {
				date = Timestamp.valueOf(dateString);
			}
		}while(!(EntryVerification.isValidDate(dateString) || (nullable && dateString != null)));
		
		return date;
	}	
	
	private static boolean isValidDate(String date) {
		try {
			Timestamp.valueOf(date);
		}catch(IllegalArgumentException e) {
			return false;
		}
		
		return true;
	}
	
	public static int readCompanyId(){
		System.out.println("What's the company id ?");
		int companyId = -1;
		
		do {
			companyId = EntryVerification.readInteger("The company id should be an integer");
			
			if(!CompanyService.getInstance().existentById(companyId)){
				//logger.error("The company does not exist\n");
			}
		}while(!CompanyService.getInstance().existentById(companyId));
		
		return companyId;
	}
}
