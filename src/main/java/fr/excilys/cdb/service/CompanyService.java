package fr.excilys.cdb.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.excilys.cdb.dto.CompanyDto;
import fr.excilys.cdb.mapper.CompanyMapper;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.persistance.CompanyRepo;

@Service
public class CompanyService implements IService<Company, CompanyDto>{
	
	private CompanyRepo companyRepo;
	private CompanyMapper companyMapper;
	
	public CompanyService(CompanyRepo companyRepo, CompanyMapper companyMapper) {
		this.companyRepo = companyRepo;
		this.companyMapper = companyMapper;
	}
	
	public Collection<CompanyDto> getAll() {
		List<Company> companies = new ArrayList<>();
		companies = this.companyRepo.findAll();
		
		return dtoGenerator(companies);
	}

	@Override
	public CompanyDto getId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company add(CompanyDto object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company update(CompanyDto object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompanyDto object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countSearch(String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean existentById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Collection<CompanyDto> dtoGenerator(List<Company> companies) {
		List<CompanyDto> companiesDto = new ArrayList<>();
		
		for(Company element : companies) {
			companiesDto.add(this.companyMapper.objectToDto(element));
		}
		
		return companiesDto;
	}

}
