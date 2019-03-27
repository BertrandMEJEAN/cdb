package fr.excilys.cdb.view.menu;

import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.exception.CustomException;
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
		
//		try {			
//			Computer createdComputer = this.computerService.update(computer);
//			System.out.println("Successfully updated "+ createdComputer);
//		}catch(CustomException e) {
//			System.out.println("Error update "+ computer);
//		}
	}
}
