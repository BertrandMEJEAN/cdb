package fr.excilys.cdb.view.menu;

import java.util.Optional;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.view.View;

public class MenuAddComputer extends MenuComputerForm {
	
	private static MenuAddComputer INSTANCE;
	
	public static MenuAddComputer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MenuAddComputer();
		}
		return INSTANCE;
	}
	
	public void show(){
		Computer computer = form();
		
		
		Optional<Computer> createdComputer = ComputerService.getInstance().add(computer);
		
		if(createdComputer.isPresent()) {
			View.logger.info("Computer successfully added "+ createdComputer.get() +"\n");
		}else {
			View.logger.error("Error creating "+ computer +"\n");
		}
	}
}
