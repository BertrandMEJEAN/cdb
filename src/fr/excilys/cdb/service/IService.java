package fr.excilys.cdb.service;

import java.util.Collection;

public interface IService<T> {
	
	public T getId(int id);
	public Collection<T> getAll();
	public T add(T object);
	public T update(T object);
	public boolean delete(T object);
	public boolean deleteById(int id);
	public boolean existentById(int id);

}
