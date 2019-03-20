package fr.excilys.cdb.configuration;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
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
@ComponentScan(basePackages = {"fr.excilys.cdb.mapper",
		"fr.excilys.cdb.controller",
		"fr.excilys.cdb.validator",
		"fr.excilys.cdb.exception",
		"fr.excilys.cdb.service",
		"fr.excilys.cdb.persistance",
		"fr.excilys.cdb.view",
		"fr.excilys.cdb.view.menu"})
@PropertySource("classpath:config.properties")
public class SpringConfig implements WebMvcConfigurer, WebApplicationInitializer {
	
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

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
		new DispatcherServlet(rootContext));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
	
	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.FRENCH);
		localeResolver.setCookieName("CDB-cookie");
		localeResolver.setCookieMaxAge(3600);
		
		return localeResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localInterceptor());
	}
	
}
