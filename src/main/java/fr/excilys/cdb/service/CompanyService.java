package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.persistance.CompanyRepo;

@Service
public class CompanyService /*implements IService<Company>*/{
	
	private CompanyRepo companyRepo;
	
	public CompanyService(CompanyRepo companyRepo) {
		this.companyRepo = companyRepo;
	}

//	public Optional<Company> getId(int id) {
//		return this.companyDao.getId(id);
//	}
//
	public Collection<Company> getAll() {
		return this.companyRepo.findAll();
	}
//
//	public int add(Company object) {
//		return this.companyDao.add(object);
//	}
//
//	public boolean update(Company object) {
//		return this.companyDao.update(object);
//	}
//
//	public int delete(Company object) {
//		return this.companyDao.delete(object);
//	}
//
//	public boolean existentById(int id) {
//		return this.companyDao.existentById(id);
//	}
//
//	public CompanyDAO getCompanyDao() {
//		return companyDao;
//	}
//
//	public void setcompanyDao(CompanyDAO companyDao) {
//		this.companyDao = companyDao;
//	}

}
