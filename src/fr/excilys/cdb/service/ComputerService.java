package fr.excilys.cdb.service;

import java.util.Collection;

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
		this.setComputerDAO(new ComputerDAO());
		this.setCompanyDAO(new CompanyDAO());
	}

	@Override
	public Computer getId(int id) {
		return this.computerDAO.getId(id);
	}
	
	public ComputerDetails getDetailsByComputerId(int id) {
		Computer computer = this.computerDAO.getId(id);
		if(computer == null) {
			//throw new EntityNotFoundException();
		}
		
		Company company = this.companyDao.getId(computer.getCompId());
		
		return new ComputerDetails(computer, company);
	}

	@Override
	public Collection<Computer> getAll() {
		return this.computerDAO.getAll();
	}

	@Override
	public Computer add(Computer object) {
		return this.computerDAO.add(object);
	}

	@Override
	public Computer update(Computer object) {
		return this.computerDAO.update(object);
	}

	@Override
	public boolean delete(Computer object) {
		return this.computerDAO.delete(object);
	}

	@Override
	public boolean deleteById(int id) {
		return this.computerDAO.deleteById(id);
	}

	@Override
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
