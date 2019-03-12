package fr.excilys.cdb.mapper;

import org.springframework.stereotype.Component;

import fr.excilys.cdb.dto.CompanyDto;
import fr.excilys.cdb.exception.ValidatorException;
import fr.excilys.cdb.model.Company;

@Component
public class CompanyMapper implements IMapper<Company, CompanyDto>{
	
	public CompanyMapper() {
		
	}
	
	public CompanyDto objectToDto(Company object) {
		CompanyDto dto = new CompanyDto();
		dto.setId(object.getId());
		dto.setName(object.getName());
		
		return dto;		
	}
	
	public Company dtoToObject(CompanyDto object) throws ValidatorException {
		Company company = new Company();
		company.setId(object.getId());
		company.setName(object.getName());
		
		return company;
	}

}
