package fr.excilys.cdb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import fr.excilys.cdb.service.UserService;

@Configuration
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
			.antMatchers("/add").hasAuthority("ADMIN")
			.antMatchers("/edit").hasAuthority("ADMIN")
			.antMatchers("/delete").hasAuthority("ADMIN")
			.antMatchers("/dashboard").authenticated()
			.antMatchers("/loginProcess").permitAll()
		.and()
			.formLogin()
			.loginPage("/")
			.loginProcessingUrl("/loginProcess")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/dashboard")
			.failureForwardUrl("/login?error")
		.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.logoutUrl("/logoutProcess");
	}
}
