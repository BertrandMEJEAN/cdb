package fr.excilys.cdb.configuration;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "fr.excilys.cdb.persistance")
@EnableTransactionManagement
@ComponentScan(basePackages = {"fr.excilys.cdb.mapper",
		"fr.excilys.cdb.controller",
		"fr.excilys.cdb.validator",
		"fr.excilys.cdb.exception",
		"fr.excilys.cdb.service",
		"fr.excilys.cdb.view",
		"fr.excilys.cdb.view.menu"})
@PropertySource(value= {"classpath:config.properties"})
public class SpringConfig implements WebMvcConfigurer {
	
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
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/css/*").addResourceLocations("/css/");
		registry.addResourceHandler("/js/*").addResourceLocations("/js/");
		registry.addResourceHandler("/fonts/*").addResourceLocations("/fonts/");
	}
	
	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		localeResolver.setCookieName("CDB-cookie");
		localeResolver.setCookieMaxAge(3600);
		
		return localeResolver;
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:lang");
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
	
	@Bean
	public LocaleChangeInterceptor localInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		
		return interceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localInterceptor());
	}
	
}
