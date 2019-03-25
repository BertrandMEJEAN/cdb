package fr.excilys.cdb.service;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {
	
	public Optional<T> getId(int id);
	public Collection<T> getAll();
	public T add(T object);
	public T update(T object);
	public void delete(T object);
	public long count();
	public long countSearch(String search);
	public boolean existentById(int id);

}
