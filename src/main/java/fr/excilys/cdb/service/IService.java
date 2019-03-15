package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import fr.excilys.cdb.exception.DAOException;

public interface IService<T> {
	
	public Optional<T> getId(int id);
	public Collection<T> getAll();
	public int add(T object);
	public boolean update(T object);
	public boolean delete(T object);
	public boolean deleteById(int id);
	public boolean existentById(int id);

}
