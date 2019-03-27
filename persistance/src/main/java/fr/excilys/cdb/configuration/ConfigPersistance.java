package fr.excilys.cdb.configuration;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "fr.excilys.cdb.persistance")
@PropertySource(value= {"classpath:config.properties"})
public class ConfigPersistance {
	
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
		
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(HikariDataSource hikariSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setPackagesToScan(new String[] {"fr.excilys.cdb.model"});
		em.setDataSource(hikariSource);
		return em;		
	}
	
	@Bean 
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
