package fr.excilys.cdb.view.menu;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;

public class MenuAddComputer extends MenuComputerForm {
	
	private static MenuAddComputer INSTANCE;
	
	public static MenuAddComputer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MenuAddComputer();
		}
		return INSTANCE;
	}
	
	public void show() {
		Computer computer = form();
		
		Computer createdComputer = ComputerService.getInstance().add(computer);
		
		if(createdComputer != null) {
			//getLogger().info("Computer successfully added "+ createdComputer+"\n");
		}else {
			//getLogger().error("Error creating "+ computer +"\n");
		}
	}
}
