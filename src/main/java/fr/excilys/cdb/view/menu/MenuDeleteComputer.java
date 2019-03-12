package fr.excilys.cdb.view.menu;

import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.view.View;

public class MenuDeleteComputer extends Menu {
	
	private static MenuDeleteComputer INSTANCE;
	
	public static MenuDeleteComputer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MenuDeleteComputer();
		}
		return INSTANCE;
	}
	
	@Override
	public void show() {
		
		System.out.println("What's the computer id ?");
		int computerId = EntryVerification.readInteger("The computer id should be an integer");
		
//		if(ComputerService.getInstance().deleteById(computerId)) {
//			View.logger.info("Successsfully deleted computer %d", computerId);
//		}else {
//			View.logger.error("An error happened while trying to delete computer %d",computerId);
//		}
	}

}
