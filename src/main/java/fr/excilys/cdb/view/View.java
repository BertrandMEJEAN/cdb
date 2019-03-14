package fr.excilys.cdb.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.excilys.cdb.view.menu.*;


public class View {
	
	@Autowired
	private MenuAllComputer menuAllComputer;
	
	@Autowired
	private MenuAllCompany menuAllCompany;
	
	@Autowired
	private MenuDetailsComputer menuDetailsComputer;
	
	@Autowired
	private MenuAddComputer menuAddComputer;
	
	@Autowired
	private MenuUpdateComputer menuUpdateComputer;
	
	@Autowired
	private MenuDeleteComputer menuDeleteComputer;
	
	@Autowired
	private MenuDeleteCompany menuDeleteCompany;
	
	
	public static Logger logger;
	
	static {
		View.logger = LoggerFactory.getLogger(View.class);
		}
	
	public View() {
		
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
	
	public void run() {
		MenuChoice choice = null;
		
		do {
			choice = userRequest();
			
			switch(choice) {
				case ALL_COMPUTER:
					this.menuAllComputer.show();
				break;
				
				case ALL_COMPANY:
					this.menuAllCompany.show();
				break;
				
				case COMPUTER_DETAILS:
					this.menuDetailsComputer.show();
				break;
				
				case ADD_COMPUTER:
					this.menuAddComputer.show();
				break;
				
				case UPDATE_COMPUTER:
					this.menuUpdateComputer.show();
				break;
				
				case DELETE_COMPUTER:
					this.menuDeleteComputer.show();
				break;
				
				case DELETE_COMPANY:
					this.menuDeleteCompany.show();
				
				case QUIT:
					System.out.println("Good Bye");
				break;
			}
		}while(choice != MenuChoice.QUIT);
	}
}
