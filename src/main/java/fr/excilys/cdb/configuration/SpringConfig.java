package fr.excilys.cdb.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"fr.excilys.cdb.mapper",
		"fr.excilys.cdb.dto",
		"fr.excilys.cdb.servlet",
		"fr.excilys.cdb.validator",
		"fr.excilys.cdb.exception",
		"fr.excilys.cdb.model",
		"fr.excilys.cdb.service",
		"fr.excilys.cdb.persistance"})
public class SpringConfig {
	
	

}
