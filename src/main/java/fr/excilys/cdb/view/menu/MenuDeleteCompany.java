package fr.excilys.cdb.view.menu;

import org.springframework.stereotype.Component;

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
		int companyId = EntryVerification.readInteger("The computer id should be an integer");
		
		/*if(this.companyService.deleteById(companyId) != 0) {
			View.logger.info("Successsfully deleted company %d", companyId);
		}else {
			View.logger.error("An error happened while trying to delete company %d",companyId);
		}*/
	}

}
