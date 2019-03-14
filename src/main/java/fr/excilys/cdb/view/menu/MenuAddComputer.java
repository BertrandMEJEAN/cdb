package fr.excilys.cdb.view.menu;

import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.view.View;

@Component
public class MenuAddComputer extends MenuComputerForm{
	
	private ComputerService computerService;
	
	public MenuAddComputer(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	public void show(){
		Computer computer = form();
		
		
		Optional<Computer> createdComputer = this.computerService.add(computer);
		
		if(createdComputer.isPresent()) {
			View.logger.info("Computer successfully added "+ createdComputer.get() +"\n");
		}else {
			View.logger.error("Error creating "+ computer +"\n");
		}
	}
}
