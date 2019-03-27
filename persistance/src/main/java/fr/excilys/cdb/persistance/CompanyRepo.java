package fr.excilys.cdb.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.excilys.cdb.model.Company;

public interface CompanyRepo extends CrudRepository<Company, Integer> {

	public List<Company> findAll();
}
