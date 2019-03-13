package fr.excilys.cdb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"fr.excilys.cdb.mapper",
		"fr.excilys.cdb.servlet",
		"fr.excilys.cdb.validator",
		"fr.excilys.cdb.exception",
		"fr.excilys.cdb.service",
		"fr.excilys.cdb.persistance"})
@PropertySource("classpath:config.properties")
public class SpringConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public HikariDataSource hikariSource() {
		
		HikariDataSource hikariData = new HikariDataSource();
		
		hikariData.setDriverClassName(environment.getRequiredProperty("dbdriver"));
		hikariData.setJdbcUrl(environment.getRequiredProperty("dbconf"));
		hikariData.setUsername(environment.getRequiredProperty("dbuser"));
		hikariData.setPassword(environment.getRequiredProperty("dbpassword"));
		hikariData.setConnectionTimeout(10000L);
		
		return hikariData;
	}
}
