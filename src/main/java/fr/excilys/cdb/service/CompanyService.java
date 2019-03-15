package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.excilys.cdb.exception.DAOException;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.persistance.CompanyDAO;

@Service
public class CompanyService implements IService<Company> {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	public CompanyService() {
		
	}

	public Optional<Company> getId(int id) {
		return this.companyDAO.getId(id);
	}

	public Collection<Company> getAll() {
		return this.companyDAO.getAll();
	}

	public int add(Company object) {
		return this.companyDAO.add(object);
	}

	public boolean update(Company object) {
		return this.companyDAO.update(object);
	}

	public int delete(Company object) {
		return this.companyDAO.delete(object);
	}

	public boolean existentById(int id) {
		return this.companyDAO.existentById(id);
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

}
