package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.persistance.CompanyDAO;

@Service
public class CompanyService implements IService<Company> {
	
	private CompanyDAO companyDao;
	
	public CompanyService(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}

	public Optional<Company> getId(int id) {
		return this.companyDao.getId(id);
	}

	public Collection<Company> getAll() {
		return this.companyDao.getAll();
	}

	public int add(Company object) {
		return this.companyDao.add(object);
	}

	public boolean update(Company object) {
		return this.companyDao.update(object);
	}

	public int delete(Company object) {
		return this.companyDao.delete(object);
	}

	public boolean existentById(int id) {
		return this.companyDao.existentById(id);
	}

	public CompanyDAO getCompanyDao() {
		return companyDao;
	}

	public void setcompanyDao(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}

}
