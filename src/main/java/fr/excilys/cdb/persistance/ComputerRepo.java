package fr.excilys.cdb.persistance;

import org.springframework.data.repository.PagingAndSortingRepository;

import fr.excilys.cdb.model.Computer;

public interface ComputerRepo extends PagingAndSortingRepository<Computer, Integer> {
	
	
}
