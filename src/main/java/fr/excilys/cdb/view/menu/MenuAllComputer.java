package fr.excilys.cdb.view.menu;

import java.util.Collection;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;

public class MenuAllComputer extends Menu {

		private static MenuAllComputer INSTANCE;

		public static MenuAllComputer getInstance() {
			if(INSTANCE == null) {
				INSTANCE = new MenuAllComputer();
			}
		
		return INSTANCE;
		}
		
		@Override
		public void show() {
//			Collection<Computer> computers = ComputerService.getInstance().getAll();
//			if(computers != null) {
//				System.out.println(computers);
//			} 
		}
		
}