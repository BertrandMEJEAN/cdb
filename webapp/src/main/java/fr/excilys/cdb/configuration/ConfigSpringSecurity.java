package fr.excilys.cdb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import fr.excilys.cdb.service.UserService;

@Configuration
@Import({ConfigService.class})
@EnableWebSecurity
public class ConfigSpringSecurity extends WebSecurityConfigurerAdapter {
	
	private UserService userService;
	
	public ConfigSpringSecurity(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordencoder());
	}
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/add").hasAuthority("ADMIN_USER")
			.antMatchers("/edit").hasAuthority("ADMIN_USER")
			.antMatchers("/delete").hasAuthority("ADMIN_USER")
			.antMatchers("/").permitAll()
			.antMatchers("/loginProcess").permitAll()
		.and()
			.formLogin()
			.loginProcessingUrl("/loginProcess")
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}
}
