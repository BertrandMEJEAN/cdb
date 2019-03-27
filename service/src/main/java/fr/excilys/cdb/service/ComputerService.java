package fr.excilys.cdb.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.exception.ValidatorException;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.persistance.ComputerRepo;

@Service
public class ComputerService implements IService<Computer, ComputerDto> {

	private ComputerRepo computerRepo;
	private ComputerMapper computerMapper;
	
	public ComputerService(ComputerRepo computerRepo, ComputerMapper computerMapper) {
		this.computerRepo = computerRepo;
		this.computerMapper = computerMapper;
	}

	public ComputerDto getId(int id) {
		
		Optional<Computer> computer = this.computerRepo.findById(id);
		ComputerDto computerDto = this.computerMapper.objectToDto(computer.get());
		
		return computerDto;
	}
	
	public long count() {
		return this.computerRepo.count();
	}
	
	public long countSearch(String pSearch) {
		return this.computerRepo.countByNameContaining(pSearch);
	}
	
	public Collection<ComputerDto> getPage(Pagination page){
		List<Computer> computersPage = new ArrayList<>();
		
		computersPage = this.computerRepo.findAll(PageRequest.of((page.getPage()-1),page.getPageSize())).getContent();
		
		return dtoGenerator(computersPage);
	}
	
	public Collection<ComputerDto> getPageContains(Pagination page){
		List<Computer> computersPage = new ArrayList<>();
		
		computersPage = this.computerRepo.findAllByNameContains(page.getSearch(), PageRequest.of((page.getPage()-1),page.getPageSize())).getContent();
		
		return dtoGenerator(computersPage);
	}
	
	public Collection<ComputerDto> getPageOrderBy(Pagination page){
		List<Computer> computersPage = new ArrayList<>();

		computersPage = this.computerRepo.findAllOrderBy(ascOrDesc(page)).getContent();
		
		return dtoGenerator(computersPage);
	}
	
	public Collection<ComputerDto> getPageContainsAndOrderBy(Pagination page){
		List<Computer> computersPage = new ArrayList<>();
		
		computersPage = this.computerRepo.findAllContainsOrderBy(page.getSearch(), ascOrDesc(page)).getContent();
		
		return dtoGenerator(computersPage);
	}

	public Collection<ComputerDto> getAll() {
		return null;
	}

	public Computer add(ComputerDto object) throws ValidatorException {		
		
		return this.computerRepo.save(objectGenerator(object));
	}

	public Computer update(ComputerDto object) throws ValidatorException {
		
		return this.computerRepo.save(objectGenerator(object));
	}

	public void delete(ComputerDto object) throws ValidatorException {
		
		this.computerRepo.delete(objectGenerator(object));
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
	
	private Collection<ComputerDto> dtoGenerator(List<Computer> computers){
		List<ComputerDto> computersDto = new ArrayList<>();
		
		for(Computer element : computers) {
			computersDto.add(this.computerMapper.objectToDto(element));
		}
		
		return computersDto;
	}
	
	private ComputerDto dtoGenerator(Computer computer) {
		ComputerDto computerDto = this.computerMapper.objectToDto(computer);
		
		return computerDto;
	}
	
	private Computer objectGenerator(ComputerDto computerDto) throws ValidatorException {
		Computer computer = this.computerMapper.dtoToObject(computerDto);
		
		return computer;
		
	}
}
