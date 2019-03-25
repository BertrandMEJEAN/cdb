package fr.excilys.cdb.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.persistance.ComputerRepo;

@Service
public class ComputerService implements IService<Computer> {

	private ComputerRepo computerRepo;
	
	public ComputerService(ComputerRepo computerRepo) {
		this.computerRepo = computerRepo;
	}

	public Optional<Computer> getId(int id) {
		return this.computerRepo.findById(id);
	}
	
	public long count() {
		return this.computerRepo.count();
	}
	
	public long countSearch(String pSearch) {
		return this.computerRepo.countByNameContaining(pSearch);
	}
	
	public Collection<Computer> getPage(Pagination page){
		List<Computer> computersPage = new ArrayList<>();
		
		computersPage = this.computerRepo.findAll(PageRequest.of((page.getPage()-1),page.getPageSize())).getContent();
		
		return computersPage;
	}
	
	public Collection<Computer> getPageContains(Pagination page){
		List<Computer> computersPage = new ArrayList<>();
		
		computersPage = this.computerRepo.findAllByNameContains(page.getSearch(), PageRequest.of((page.getPage()-1),page.getPageSize())).getContent();
		
		return computersPage;
	}
	
	public Collection<Computer> getPageOrderBy(Pagination page){
		List<Computer> computersPage = new ArrayList<>();

		computersPage = this.computerRepo.findAllOrderBy(ascOrDesc(page)).getContent();
		
		return computersPage;
	}
	
	public Collection<Computer> getPageContainsAndOrderBy(Pagination page){
		List<Computer> computersPage = new ArrayList<>();
		
		computersPage = this.computerRepo.findAllContainsOrderBy(page.getSearch(), ascOrDesc(page)).getContent();
		
		return computersPage;
	}

	public Collection<Computer> getAll() {
		return null;
	}

	public Computer add(Computer object) {
		System.out.println(object.toString());
		return this.computerRepo.save(object);
	}

	public Computer update(Computer object) {
		return this.computerRepo.save(object);
	}

	public void delete(Computer object) {
		this.computerRepo.delete(object);
	}

	public boolean existentById(int id) {
		return this.computerRepo.existsById(id);
	}
	
	private PageRequest ascOrDesc(Pagination page) {
		PageRequest request;
		
		if(page.getSort().equals("ASC")) {
			request = PageRequest.of((page.getPage()-1), page.getPageSize(), Sort.by(page.getOrder()).ascending());
		}else {
			request = PageRequest.of((page.getPage()-1), page.getPageSize(), Sort.by(page.getOrder()).descending());
		}
		
		return request;
	}
}
