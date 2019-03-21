package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.excilys.cdb.exception.CustomException;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.ComputerDetails;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.persistance.ComputerDAO;
import fr.excilys.cdb.persistance.ComputerRepo;

@Service
public class ComputerService implements IService<Computer> {
	
//	private ComputerDAO computerDao;
//	private CompanyDAO companyDao;
	private ComputerRepo computerRepo;
	
	public ComputerService(/*ComputerDAO computerDao, CompanyDAO companyDao, */ComputerRepo computerRepo) {
//		this.companyDao = companyDao;
//		this.computerDao = computerDao;
		this.computerRepo = computerRepo;
	}

	public Optional<Computer> getId(int id) {
		return this.computerRepo.findById(id);
	}
	
	public long countComputer() {
		return this.computerRepo.count();
	}
	
	public int countComputer(String pSearch) {
		return 0;//this.computerDao.countComputer(pSearch);
	}
	
	public Collection<Computer> getPageComputer(Pagination page){
		
		return this.computerRepo.findAll(PageRequest.of((page.getPage()-1),page.getPageSize())).getContent();
		}

	public Collection<Computer> getAll() {
		return null; // this.computerDao.getAll();
		//return this.computerRepo.findAll();
	}

	public int add(Computer object) {
		return 0;//this.computerDao.add(object);
	}

	public boolean update(Computer object) {
		return true;//this.computerDao.update(object);
	}

	public int delete(Computer object) {
		return 0;//this.computerDao.delete(object);
	}

	public boolean existentById(int id) {
		return true;//this.computerDao.existentById(id);
	}

//	public ComputerDAO getcomputerDao() {
//		return computerDao;
//	}
//
//	public void setcomputerDao(ComputerDAO computerDao) {
//		this.computerDao = computerDao;
//	}
//
//	public CompanyDAO getCompanyDAO() {
//		return companyDao;
//	}
//
//	public void setCompanyDAO(CompanyDAO companyDao) {
//		this.companyDao = companyDao;
//	}
}
