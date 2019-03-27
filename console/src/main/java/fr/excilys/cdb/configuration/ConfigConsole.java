package fr.excilys.cdb.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"fr.excilys.cdb.view","fr.excilys.cdb.view.menu"})
public class ConfigConsole {

}
