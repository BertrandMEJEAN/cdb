package fr.excilys.cdb.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.excilys.cdb.model.User;
import fr.excilys.cdb.persistance.UserRepo;
import fr.excilys.cdb.persistance.UserRoleRepo;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepo userRepo;
	private UserRoleRepo userRoleRepo;
	
	public UserService(UserRepo userRepo, UserRoleRepo userRoleRepo) {
		this.userRepo = userRepo;
		this.userRoleRepo = userRoleRepo;
	}
	
	public User getUser(String Username) {
		return this.userRepo.findByUser(Username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByUser(username);
		if(user == null) {
			throw new UsernameNotFoundException("No user found with username "+ username);
		}else {
			List<String> userRoles = this.userRoleRepo.findUserRoleByUsername(username);
			return new CustomUserDetails(user, userRoles);
		}
	}
}
