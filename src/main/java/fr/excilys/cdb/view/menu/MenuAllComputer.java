package fr.excilys.cdb.view.menu;

import java.util.Collection;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;

@Component
public class MenuAllComputer extends Menu {
	
		private ComputerService computerService;
		
		public MenuAllComputer(ComputerService computerService) {
			this.computerService = computerService;
		}
		
		@Override
		public void show() {
			Collection<Computer> computers = this.computerService.getAll();
			if(computers != null) {
				System.out.println(computers);
			} 
		}
		
}