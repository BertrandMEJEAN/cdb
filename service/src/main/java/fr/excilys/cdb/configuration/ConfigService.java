package fr.excilys.cdb.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ConfigBinding.class, ConfigPersistance.class})
@ComponentScan(basePackages="fr.excilys.cdb.service")
public class ConfigService {

}
