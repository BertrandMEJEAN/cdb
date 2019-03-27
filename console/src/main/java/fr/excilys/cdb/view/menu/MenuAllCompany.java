package fr.excilys.cdb.view.menu;

import java.util.Collection;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.service.CompanyService;

@Component
public class MenuAllCompany extends Menu {

	private CompanyService companyService;
	
	public MenuAllCompany(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	public void show() {
//		Collection<Company> companies = this.companyService.getAll();
//		if(companies != null) {
//			System.out.println(companies);
//		}
	}

}
