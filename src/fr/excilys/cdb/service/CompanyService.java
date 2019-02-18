package fr.excilys.cdb.service;

import java.util.Collection;

import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.persistance.CompanyDAO;

public class CompanyService implements IService<Company> {
	
	private static CompanyService INSTANCE;
	private CompanyDAO companyDAO;
	
	public CompanyService() {
		this.setCompanyDAO(new CompanyDAO());
	}
	
	public static CompanyService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CompanyService();
		}
		return INSTANCE;
	}

	@Override
	public Company getId(int id) {
		return this.companyDAO.getId(id);
	}

	@Override
	public Collection<Company> getAll() {
		return this.companyDAO.getAll();
	}

	@Override
	public Company add(Company object) {
		return this.companyDAO.add(object);
	}

	@Override
	public Company update(Company object) {
		return this.companyDAO.update(object);
	}

	@Override
	public boolean delete(Company object) {
		return this.companyDAO.delete(object);
	}

	@Override
	public boolean deleteById(int id) {
		return this.companyDAO.deleteById(id);
	}

	@Override
	public boolean existentById(int id) {
		return this.companyDAO.deleteById(id);
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

}
