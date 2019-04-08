package fr.excilys.cdb.view.menu;


import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.exception.CustomException;
//import fr.excilys.cdb.model.ComputerDetails;
import fr.excilys.cdb.service.ComputerService;

@Component
public class MenuDetailsComputer extends Menu {
	
	private ComputerService computerService;
	
	public MenuDetailsComputer(ComputerService computerService) {
		this.computerService = computerService;
	}

	@Override
	public void show() {
		
//		System.out.println("Enter id of a computer :");
//		int computerId = EntryVerification.readInteger("The choice should be an integer");
//		
//		Optional<ComputerDetails> computerDetails = Optional.empty();
//		
//		try {
//			computerDetails = Optional.of(this.computerService.getDetailsByComputerId(computerId));			
//		}catch(CustomException e) {
//			
//		}
//		
//		if(computerDetails.isPresent()) {
//			System.out.println(computerDetails);
//		}		
	}
}
