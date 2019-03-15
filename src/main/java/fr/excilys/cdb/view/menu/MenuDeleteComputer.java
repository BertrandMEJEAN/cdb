package fr.excilys.cdb.view.menu;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.view.View;

@Component
public class MenuDeleteComputer extends Menu {
	
	private ComputerService computerService;
	
	public MenuDeleteComputer(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	@Override
	public void show() {
		
		System.out.println("What's the computer id ?");
		int computerId = EntryVerification.readInteger("The computer id should be an integer");
		
		/*if(this.computerService.deleteById(computerId) != 0) {
			View.logger.info("Successsfully deleted computer %d", computerId);
		}else {
			View.logger.error("An error happened while trying to delete computer %d",computerId);
		}*/
	}

}
