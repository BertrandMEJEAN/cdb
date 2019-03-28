package fr.excilys.cdb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.excilys.cdb.model.User;
import fr.excilys.cdb.persistance.UserRepo;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepo userRepo;
	
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByUser(username);
		if(user == null) {
			throw new UsernameNotFoundException("No user found with username "+username);
		}else {
			return new CustomUserDetails(user);
		}
	}
}
