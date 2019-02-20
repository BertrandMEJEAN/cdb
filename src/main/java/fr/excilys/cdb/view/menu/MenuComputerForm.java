package fr.excilys.cdb.view.menu;

import java.time.LocalDate;
import java.util.Optional;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.Computer.ComputerBuilder;
import fr.excilys.cdb.view.View;

public abstract class MenuComputerForm extends Menu {

	protected Computer form(){
		String name = EntryVerification.readComputerName();
		
		System.out.println("What's the computer introduction date (dd-MM-yyyy) or null ?");
		Optional<LocalDate> in = Optional.of(EntryVerification.readDate(true));
		
		Optional<LocalDate> out = Optional.empty();
		if(in.isPresent()) {
			System.out.println("What's the computer discontinued date (dd-MM-yyyy) or null ?");
			boolean validOutDate = false;
			
			do {
				
				out = Optional.of(EntryVerification.readDate(true));
				validOutDate = (out.isPresent()) ? in.get().isBefore(out.get()) : true;
				
				if(!validOutDate) {
					View.logger.error("The discontinued date should be after the introduction date or null\n");
				}
			}while(!validOutDate);
		}
		
		int companyId = EntryVerification.readCompanyId();
		
		Computer computer = new ComputerBuilder()
				.setName(name)
				.setIn(in.get())
				.setOut(out.get())
				.setCompId(companyId)
				.build();
		
		return computer;
	}
}
