package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.excilys.cdb.exception.CustomException;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.ComputerDetails;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.persistance.ComputerDAO;

@Service
public class ComputerService implements IService<Computer> {
	
	private ComputerDAO computerDao;
	private CompanyDAO companyDao;
	
	public ComputerService(ComputerDAO computerDao, CompanyDAO companyDao) {
		this.companyDao = companyDao;
		this.computerDao = computerDao;
	}

	public Optional<Computer> getId(int id) {
		return this.computerDao.getId(id);
	}
	
	public int countComputer() {
		return this.computerDao.countComputer();
	}
	
	public int countComputer(String pSearch) {
		return this.computerDao.countComputer(pSearch);
	}
	
	public Collection<Computer> getPageComputer(Pagination page){
		
		if(page.getPage()>page.getMaxPage()) {
			throw new CustomException();
		}
		
		return this.computerDao.getPageComputer(page);
	}
	
	public ComputerDetails getDetailsByComputerId(int id) {
		Optional<Computer> computer = this.computerDao.getId(id);
		if(computer == null) {
			//throw new EntityNotFoundException();
		}
		Optional<Company> company = this.companyDao.getId(computer.get().getCompany().getId());
		
		return new ComputerDetails(computer.get(), company.get());
	}

	public Collection<Computer> getAll() {
		return this.computerDao.getAll();
	}

	public int add(Computer object) {
		return this.computerDao.add(object);
	}

	public boolean update(Computer object) {
		return this.computerDao.update(object);
	}

	public int delete(Computer object) {
		return this.computerDao.delete(object);
	}

	public boolean existentById(int id) {
		return this.computerDao.existentById(id);
	}

	public ComputerDAO getcomputerDao() {
		return computerDao;
	}

	public void setcomputerDao(ComputerDAO computerDao) {
		this.computerDao = computerDao;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDao;
	}

	public void setCompanyDAO(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
}
