package fr.excilys.cdb.view.menu;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;

public class MenuUpdateComputer extends MenuComputerForm {

	private static MenuUpdateComputer INSTANCE;
	
	public static MenuUpdateComputer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MenuUpdateComputer();
		}
		return INSTANCE;
	}
	
	public void show() {
		
		System.out.println("What's the computer id ?");
		int computerId = Integer.valueOf(EntryVerification.readString());
		Computer computer = form();
		computer.setId(computerId);
		
		Computer createdComputer = ComputerService.getInstance().update(computer);
		
		if(createdComputer != null) {
			System.out.println("Successfully updated "+ createdComputer);
		}else {
			System.out.println("Error update "+ createdComputer);
		}
	}
}
