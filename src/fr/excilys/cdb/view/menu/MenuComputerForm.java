package fr.excilys.cdb.view.menu;

import java.sql.Date;
import java.sql.Timestamp;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.Computer.ComputerBuilder;

public abstract class MenuComputerForm extends Menu {

	protected Computer form(){
		String name = EntryVerification.readComputerName();
		
		System.out.println("What's the computer introduction date (YYYY-mm-dd HH:mm:ss) or null ?");
		Timestamp in = EntryVerification.readDate(true);
		
		Timestamp out = null;
		if(in != null) {
			System.out.println("What's the computer discontinued date (YYYY-mm-dd HH:mm:ss) or null ?");
			boolean validOutDate = false;
			
			do {
				
				out = EntryVerification.readDate(true);
				validOutDate = (out != null) ? in.before(out) : true;
				
				if(!validOutDate) {
					//getLogger().error("The discontinued date should be after the introduction date or null\n");
				}
			}while(!validOutDate);
		}
		
		int companyId = EntryVerification.readCompanyId();
		
		Computer computer = new ComputerBuilder()
				.setName(name)
				.setIn(in)
				.setOut(out)
				.setCompId(companyId)
				.build();
		
		return computer;
	}
}
