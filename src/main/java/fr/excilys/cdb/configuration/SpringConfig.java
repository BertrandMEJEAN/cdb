package fr.excilys.cdb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"fr.excilys.cdb.mapper",
		"fr.excilys.cdb.servlet",
		"fr.excilys.cdb.validator",
		"fr.excilys.cdb.exception",
		"fr.excilys.cdb.service",
		"fr.excilys.cdb.persistance",
		"fr.excilys.cdb.view",
		"fr.excilys.cdb.view.menu"})
@PropertySource("classpath:config.properties")
public class SpringConfig {
	
	@Value("${dbdriver}")
	String driver;
	
	@Value("${dbconf}")
	String conf;
	
	@Value("${dbuser}")
	String user;
	
	@Value("${dbpassword}")
	String password;
	
	@Bean
	public HikariDataSource hikariSource() {
		
		HikariDataSource hikariData = new HikariDataSource();
		
		hikariData.setDriverClassName(driver);
		hikariData.setJdbcUrl(conf);
		hikariData.setUsername(user);
		hikariData.setPassword(password);
		hikariData.setConnectionTimeout(10000L);
		
		return hikariData;
	}
}
