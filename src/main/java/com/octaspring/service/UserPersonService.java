package com.octaspring.service;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.UserPerson;

public class UserPersonService implements UserPersonInterface{
	
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public UserPersonService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(UserPerson userPerson) {
		// TODO Auto-generated method stub
		System.out.println("Resgistrar usuario");
		
		sql= "INSERT INTO user_person(name, lastname, email, password, registered, gender, status, photo) values(?, ?, ?, ?, ?, ?, ?, ?)";
		userPerson.setRegistered(new Date());
		userPerson.setStatus(1);
		userPerson.setPhoto("default.jpg");
		jdbcTemplate.update(sql, userPerson.getName(), userPerson.getLastname(), userPerson.getEmail(), userPerson.getPassword(), userPerson.getRegistered(), userPerson.getGender(), userPerson.getStatus(), userPerson.getPhoto());
	}

	@Override
	public void update(UserPerson userPerson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserPerson> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPerson> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
