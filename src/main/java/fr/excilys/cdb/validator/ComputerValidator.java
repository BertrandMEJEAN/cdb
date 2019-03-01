package fr.excilys.cdb.validator;

import fr.excilys.cdb.exception.ValidatorException;
import fr.excilys.cdb.model.Computer;

public class ComputerValidator {

	private static ComputerValidator INSTANCE;
	
	private ComputerValidator() {
		
	}
	
	public static ComputerValidator getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ComputerValidator();
		}
		return INSTANCE;
	}
	
	public void validateComputer(Computer computer) throws ValidatorException{
		if(objectNotNull(computer)) {
			throw new ValidatorException("Require data to create computer");
		}else if(correctDateAssignemet(computer)) {
			throw new ValidatorException("The discontinued date can be set or assign before introduced date");
		}
	}
	
	private boolean objectNotNull(Computer computer){
		boolean check = (computer == null ? true : false);
		return check;
	}

	private boolean correctDateAssignemet(Computer computer) {
		boolean check = ((computer.getOut() != null && computer.getIn() == null) || (computer.getOut().isBefore(computer.getIn())) ? true : false );
		return check;
	}
}
