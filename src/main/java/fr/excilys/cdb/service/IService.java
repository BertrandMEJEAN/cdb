package fr.excilys.cdb.service;

import java.util.Collection;

import fr.excilys.cdb.exception.ValidatorException;

public interface IService<T, D> {
	
	public D getId(int id);
	public Collection<D> getAll();
	public T add(D object) throws ValidatorException;
	public T update(D object) throws ValidatorException;
	public void delete(D object) throws ValidatorException;
	public long count();
	public long countSearch(String search);
	public boolean existentById(int id);
}
