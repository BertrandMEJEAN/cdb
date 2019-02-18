package fr.excilys.cdb.view.menu;


import fr.excilys.cdb.exception.CustomException;
import fr.excilys.cdb.model.ComputerDetails;
import fr.excilys.cdb.service.ComputerService;

public class MenuDetailsComputer extends Menu {
	
	private static MenuDetailsComputer INSTANCE;
	
	public static MenuDetailsComputer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MenuDetailsComputer();
		}		
		return INSTANCE;
	}

	@Override
	public void show() {
		
		System.out.println("Enter id of a computer :");
		int computerId = EntryVerification.readInteger("The choice should be an integer");
		
		ComputerDetails computerDetails = null;
		
		try {
			computerDetails = ComputerService.getInstance().getDetailsByComputerId(computerId);			
		}catch(CustomException e) {
			
		}
		
		if(computerDetails != null) {
			System.out.println(computerDetails);
		}		
	}
}
