package fr.excilys.cdb.view.menu;

import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.exception.CustomException;
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
		
//		try {
//			this.computerService.add(computer);
//			View.logger.info("Computer successfully added \n");
//		}catch(CustomException e) {
//			View.logger.error("Error creating "+ computer +"\n");
//		}
	}
}
