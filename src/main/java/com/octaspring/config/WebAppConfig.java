package com.octaspring.config;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.dao.CourseInterface;
import com.octaspring.dao.LangInterface;
import com.octaspring.dao.LevelInterface;
import com.octaspring.dao.RoleInterface;
import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.dao.UserPersonInterface;
import com.octaspring.service.CategoryService;
import com.octaspring.service.CourseService;
import com.octaspring.service.LangService;
import com.octaspring.service.LevelService;
import com.octaspring.service.RoleService;
import com.octaspring.service.SubcategoryService;
import com.octaspring.service.UserPersonService;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.octaspring.controller")
public class WebAppConfig implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(100000000);
		return multipartResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		// indicar donde estan las VISTAS
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addDialect(new SpringSecurityDialect());
		
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver viewReolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(this.templateEngine());
		return viewResolver;
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/springocta?serverTimezone=UTC");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
	}
	
	@Bean
	public LangInterface getLang() {
		return new LangService(this.getDataSource());
	}
	
	@Bean
	public UserPersonInterface getUserPerson() {
		return new UserPersonService(this.getDataSource()); 
	}
	
	@Bean
	public CategoryInterface getCategory() {
		return new CategoryService(this.getDataSource());
	}
	
	@Bean
	public RoleInterface getRole() {
		return new RoleService(this.getDataSource());
	}
	
	@Bean
	public SubcategoryInterface getSubcategory() {
		return new SubcategoryService(this.getDataSource());
	}
	
	@Bean
	public LevelInterface getLevel() {
		return new LevelService(this.getDataSource());
	}
	
	@Bean
	public CourseInterface getCourse() {
		return new CourseService(this.getDataSource());
	}
}
