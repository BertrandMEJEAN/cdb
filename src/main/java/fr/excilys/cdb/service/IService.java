package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {
	
	public Optional<T> getId(int id);
	public Collection<T> getAll();
	public Optional<T> add(T object);
	public Optional<T> update(T object);
	public boolean delete(T object);
	public boolean deleteById(int id);
	public boolean existentById(int id);

}
