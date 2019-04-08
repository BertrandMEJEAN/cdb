package fr.excilys.cdb.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.excilys.cdb.model.UserRole;

public interface UserRoleRepo extends CrudRepository<UserRole, Integer> {
	
		@Query(value = "SELECT user_role FROM role, user WHERE user.username = ? AND role.id = user.role_id", nativeQuery = true)
		public List<String> findUserRoleByUsername(String userName);
		

}
