package fr.excilys.cdb.view.menu;

import java.util.Collection;

import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.service.CompanyService;

public class MenuAllCompany extends Menu {
	
	private static MenuAllCompany INSTANCE;
	
	public static MenuAllCompany getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MenuAllCompany();
		}
		return INSTANCE;
	}
	
	public void show() {
//		Collection<Company> companies = CompanyService.getInstance().getAll();
//		if(companies != null) {
//			System.out.println(companies);
//		}
	}

}
