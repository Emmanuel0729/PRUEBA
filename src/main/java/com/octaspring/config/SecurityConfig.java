package com.octaspring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	DataSource datasource;
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/springocta?serverTimezone=UTC");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
	}
	
	public  void configure(AuthenticationManagerBuilder auth) throws Exception{
		//Permitir la autentificacion
		
		//JDBC
		
		auth.jdbcAuthentication().dataSource(this.getDataSource())
			.passwordEncoder(this.passwordEncoder())
			.usersByUsernameQuery("SELECT email, password, 'true' as enabled FROM user_person WHERE email = ?")
			.authoritiesByUsernameQuery("SELECT up.email, r.name FROM user_person up INNER JOIN user_role ur ON up.id = ur.user_person INNER JOIN role r ON r.id = ur.role WHERE email = ?");
			
		//In memory
		/*
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
			.withUser("ADMIN@email.com").password(passwordEncoder.encode("soyadmin")).roles("ADMIN")
			.and()
			.withUser("sutano@email.com").password(passwordEncoder.encode("1234")).roles("TEACHER");*/
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public void configure(HttpSecurity http) throws Exception{
		//Controlar el acceso a las RUTAS
		http.authorizeRequests()
			.antMatchers("/", "/login", "/register", "/user-create")
				.permitAll()
			.antMatchers("/**")
				.hasAnyRole("ADMIN")
			.and()
				.formLogin()
				.defaultSuccessUrl("/lang")
				.loginPage("/login")
					.permitAll()
			.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.permitAll()
			.and()
				.csrf()
				.disable();
	}
}
