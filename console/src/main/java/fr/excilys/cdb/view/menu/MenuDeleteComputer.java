package fr.excilys.cdb.view.menu;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.exception.CustomException;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.Computer.ComputerBuilder;
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
		Computer computer = new ComputerBuilder().setId(EntryVerification.readInteger("The computer id should be an integer")).build();
		
//		try{
//			this.computerService.delete(computer);
//			View.logger.info("Successsfully deleted computer %d", computer);
//		}catch(CustomException e) {
//			View.logger.error("An error happened while trying to delete computer %d",computer);
//		}
	}

}
