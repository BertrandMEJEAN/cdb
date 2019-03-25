package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.persistance.CompanyRepo;

@Service
public class CompanyService implements IService<Company>{
	
	private CompanyRepo companyRepo;
	
	public CompanyService(CompanyRepo companyRepo) {
		this.companyRepo = companyRepo;
	}
	
	public Collection<Company> getAll() {
		return this.companyRepo.findAll();
	}

	@Override
	public Optional<Company> getId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company add(Company object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company update(Company object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company object) {
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

}
