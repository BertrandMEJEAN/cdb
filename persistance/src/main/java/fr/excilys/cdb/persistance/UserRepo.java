package fr.excilys.cdb.persistance;

import org.springframework.data.repository.CrudRepository;

import fr.excilys.cdb.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {

	public User findByUser(String userName);
}
