package fr.excilys.cdb.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.excilys.cdb.view.menu.*;

public class View {
	
	public static Logger logger;
	
	static {
		View.logger = LoggerFactory.getLogger(View.class);
		}
	
	private View() {
		
	}
	
	private static View INSTANCE = null;
	
	public static View getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new View();
		}
		
		return INSTANCE;
	}
	
	private static void showMenu() {
		System.out.println("=====");
		System.out.println("COMPUTER DATABASE MENU");
		System.out.println("=====");
		System.out.println("Choose an item");
		System.out.printf("%d - List all computers\n", MenuChoice.ALL_COMPUTER.getId());
		System.out.printf("%d - List all companys\n", MenuChoice.ALL_COMPANY.getId());
		System.out.printf("%d - Show computer details\n", MenuChoice.COMPUTER_DETAILS.getId());
		System.out.printf("%d - Add a computer\n", MenuChoice.ADD_COMPUTER.getId());
		System.out.printf("%d - Update Computer\n", MenuChoice.UPDATE_COMPUTER.getId());
		System.out.printf("%d - Delete Computer\n", MenuChoice.DELETE_COMPUTER.getId());
		System.out.printf("%d - Exit\n", MenuChoice.QUIT.getId());
		System.out.printf("======\n");
		
	}

	private static MenuChoice userRequest() {
		MenuChoice choice = null;
		int choiceInt;
		
		do {
			showMenu();
			
			choiceInt = EntryVerification.readInteger("The choice must be an integer");
			choice = MenuChoice.getById(choiceInt);
			
			if(choice == null) {
				logger.error("Invalid choice");
			}
		}while(choice == null);
		
		return choice;
	}
	
	public static void run() {
		MenuChoice choice = null;
		
		do {
			choice = userRequest();
			
			switch(choice) {
				case ALL_COMPUTER:
					MenuAllComputer.getInstance().show();
				break;
				
				case ALL_COMPANY:
					MenuAllCompany.getInstance().show();
				break;
				
				case COMPUTER_DETAILS:
					MenuDetailsComputer.getInstance().show();
				break;
				
				case ADD_COMPUTER:
					MenuAddComputer.getInstance().show();
				break;
				
				case UPDATE_COMPUTER:
					MenuUpdateComputer.getInstance().show();
				break;
				
				case DELETE_COMPUTER:
					MenuDeleteComputer.getInstance().show();
				break;
				
				case QUIT:
					System.out.println("Good Bye");
				break;
			}
		}while(choice != MenuChoice.QUIT);
	}
}
