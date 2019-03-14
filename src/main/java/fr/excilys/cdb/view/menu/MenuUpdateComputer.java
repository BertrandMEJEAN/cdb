package fr.excilys.cdb.view.menu;

import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;

@Component
public class MenuUpdateComputer extends MenuComputerForm {

	/*private static MenuUpdateComputer INSTANCE;
	
	public static MenuUpdateComputer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MenuUpdateComputer();
		}
		return INSTANCE;
	}*/
	
	private ComputerService computerService;
	
	public MenuUpdateComputer(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	public void show() {
		
		System.out.println("What's the computer id ?");
		int computerId = Integer.valueOf(EntryVerification.readString());
		Computer computer = form();
		computer.setId(computerId);
		
		Optional<Computer> createdComputer = this.computerService.update(computer);
		
		if(createdComputer.isPresent()) {
			System.out.println("Successfully updated "+ createdComputer);
		}else {
			System.out.println("Error update "+ createdComputer);
		}
	}
}
