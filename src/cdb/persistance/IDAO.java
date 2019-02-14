package cdb.persistance;

import java.util.Collection;

/**
 * @author Bertrand Méjean
 * @param <T> Correspond au type d'objet à manipuler.
 */
public interface IDAO<T> {
	
	public T getId(int i);
	public Collection<T> getAll();
	public T add(T object);
	public Collection<T> addAll(Collection<T> objects);
	public T update(T object);
	public boolean delete(T object);
	public boolean deleteById(int id);
	public boolean existentById(int id);	
}
