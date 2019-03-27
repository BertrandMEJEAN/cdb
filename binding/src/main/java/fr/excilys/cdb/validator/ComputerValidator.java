package fr.excilys.cdb.validator;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.exception.ValidatorException;
import fr.excilys.cdb.model.Computer;

@Component
public class ComputerValidator {
	
	public ComputerValidator() {
		
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
		boolean check = ((computer.getOut() != null && computer.getIn() == null)? true : false );
		if(computer.getIn() != null && computer.getOut() != null) {
			check = computer.getOut().isBefore(computer.getIn());
		}
		return check;
	}
}
