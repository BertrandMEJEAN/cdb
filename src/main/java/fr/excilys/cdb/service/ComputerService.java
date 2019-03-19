package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.excilys.cdb.exception.CustomException;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.ComputerDetails;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.persistance.ComputerDAO;
import fr.excilys.cdb.service.Pagination.PaginationBuilder;

@Service
public class ComputerService implements IService<Computer> {
	
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private Pagination page;
	
	public ComputerService() {
		
	}

	public Optional<Computer> getId(int id) {
		return this.computerDAO.getId(id);
	}
	
	public int countComputer() {
		return this.computerDAO.countComputer();
	}
	
	public int countComputer(String pSearch) {
		return this.computerDAO.countComputer(pSearch);
	}
	
	public Collection<Computer> getPageComputer(Pagination page){
		
		if(page.getPage()>page.getMaxPage()) {
			throw new CustomException();
		}
		
		return this.computerDAO.getPageComputer(page);
	}
	
	public ComputerDetails getDetailsByComputerId(int id) {
		Optional<Computer> computer = this.computerDAO.getId(id);
		if(computer == null) {
			//throw new EntityNotFoundException();
		}
		Optional<Company> company = this.companyDao.getId(computer.get().getCompany().getId());
		
		return new ComputerDetails(computer.get(), company.get());
	}

	public Collection<Computer> getAll() {
		return this.computerDAO.getAll();
	}

	public int add(Computer object) {
		return this.computerDAO.add(object);
	}

	public boolean update(Computer object) {
		return this.computerDAO.update(object);
	}

	public int delete(Computer object) {
		return this.computerDAO.delete(object);
	}

	public boolean existentById(int id) {
		return this.computerDAO.existentById(id);
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDao;
	}

	public void setCompanyDAO(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
	
	private int defineMaxPage(String pSearch, int pPageSize) {
		
		float count = (pSearch == null ? countComputer() : countComputer(pSearch));
		float tmp = (count / pPageSize);
		int newMaxPage = (int) Math.ceil(tmp);
			
		return newMaxPage;
	}
	
	private int defineOffSet(int pPage, int pPageSize) {
		
		int newOffSet = (pPage-1)*pPageSize;		
		
		return newOffSet;
	}
}
