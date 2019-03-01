package fr.excilys.cdb.mapper;

import fr.excilys.cdb.dto.CompanyDto;
import fr.excilys.cdb.model.Company;

public class CompanyMapper implements IMapper<Company, CompanyDto>{
	
	private static CompanyMapper INSTANCE;
	
	private CompanyMapper() {
		
	}
	
	public static CompanyMapper getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CompanyMapper();
		}
		return INSTANCE;
	}
	
	public CompanyDto objectToDto(Company object) {
		CompanyDto dto = new CompanyDto();
		dto.setId(object.getId());
		dto.setName(object.getName());
		
		return dto;		
	}
	
	public Company dtoToObject(CompanyDto object) {
		Company company = new Company();
		company.setId(object.getId());
		company.setName(object.getName());
		
		return company;
	}

}
