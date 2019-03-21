package fr.excilys.cdb.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import fr.excilys.cdb.model.Computer;

public interface ComputerRepo extends PagingAndSortingRepository<Computer, Integer> {
	
	
}
