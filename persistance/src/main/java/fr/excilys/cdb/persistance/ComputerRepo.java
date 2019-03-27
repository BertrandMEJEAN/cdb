package fr.excilys.cdb.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import fr.excilys.cdb.model.Computer;

public interface ComputerRepo extends PagingAndSortingRepository<Computer, Integer> {
	
	public long countByNameContaining(String search);
	
	public Page<Computer> findAllOrderBy(Pageable pageable);
	
	public Page<Computer> findAllByNameContains(String search, Pageable pageable); 
	
	public Page<Computer> findAllContainsOrderBy(String search,Pageable pageable);
}
