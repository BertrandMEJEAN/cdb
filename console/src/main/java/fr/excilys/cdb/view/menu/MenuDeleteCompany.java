package fr.excilys.cdb.view.menu;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.exception.CustomException;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.service.CompanyService;
import fr.excilys.cdb.view.View;

@Component
public class MenuDeleteCompany extends Menu {

	private CompanyService companyService;
	
	public MenuDeleteCompany(CompanyService companyService) {
		this.companyService = companyService;
	}	

	@Override
	public void show() {

		System.out.println("What's the company id ?");
		Company company = new Company(EntryVerification.readInteger("The company id should be an integer"), "");
		
//		try {
//			this.companyService.delete(company);
//			View.logger.info("Successsfully deleted company %d", company);
//		}catch(CustomException e){
//			View.logger.error("An error happened while trying to delete company %d",company);
//		}
	}
}
