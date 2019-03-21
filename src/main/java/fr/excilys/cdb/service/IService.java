package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

import fr.excilys.cdb.exception.DAOException;

public interface IService<T> {
	
	public Optional<T> getId(int id);
	public Collection<T> getAll();
	public int add(T object);
	public T update(T object);
	public void delete(T object);
	public boolean existentById(int id);

}
