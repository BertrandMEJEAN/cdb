package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import fr.excilys.cdb.exception.CustomException;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.model.ComputerDetails;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.persistance.ComputerDAO;

public class ComputerService implements IService<Computer> {
	
	private static ComputerService INSTANCE;
	private ComputerDAO computerDAO;
	private CompanyDAO companyDao;
	
	public static ComputerService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ComputerService();
		}
		return INSTANCE;
	}
	
	public ComputerService() {
		this.setComputerDAO(ComputerDAO.getInstance());
		this.setCompanyDAO(CompanyDAO.getInstance());
	}

	public Optional<Computer> getId(int id) {
		return this.computerDAO.getId(id);
	}
	
	public int countComputer() {
		return this.computerDAO.countComputer();
	}
	
	public Collection<Computer> getPageComputer(int pPageSize, int pPage){
		Pagination pagination = new Pagination(pPageSize, pPage);
		
		if(pagination.getPage()>pagination.getMaxPage()) {
			throw new CustomException();
		}
		
		return this.computerDAO.getPageComputer(pagination.getPageSize(),pagination.getOffSet());
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

	public Optional<Computer> add(Computer object) {
		return this.computerDAO.add(object);
	}

	public Optional<Computer> update(Computer object) {
		return this.computerDAO.update(object);
	}

	public boolean delete(Computer object) {
		return this.computerDAO.delete(object);
	}

	public boolean deleteById(int id) {
		return this.computerDAO.deleteById(id);
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
}
