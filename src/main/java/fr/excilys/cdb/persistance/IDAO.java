package fr.excilys.cdb.persistance;

import java.util.Collection;
import java.util.Optional;

import fr.excilys.cdb.exception.DAOException;

/**
 * @author Bertrand Méjean
 * @param <T> Correspond au type d'objet à manipuler.
 */
public interface IDAO<T> {
	
	public Optional<T> getId(int i);
	public Collection<T> getAll();
	public Optional<T> add(T object);
	public Collection<T> addAll(Collection<T> objects);
	public Optional<T> update(T object);
	public boolean delete(T object) throws DAOException;
	public boolean deleteById(int id) throws DAOException;
	public boolean existentById(int id);	
}
