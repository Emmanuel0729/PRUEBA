package com.octaspring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.UserPerson;

public class UserPersonService implements UserPersonInterface{
	
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	@Autowired
	PasswordEncoder paswwordEncoder;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public UserPersonService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(UserPerson userPerson, int role) {
		// TODO Auto-generated method stub
		System.out.println("Registrar usuario");
		
		sql= "INSERT INTO user_person(name, lastname, email, password, registered, gender, status, photo) values(?, ?, ?, ?, ?, ?, ?, ?)";
		userPerson.setRegistered(new Date());
		userPerson.setStatus(1);
		userPerson.setPhoto("default.jpg");
		
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement stm = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
				stm.setString(1, userPerson.getName());
				stm.setString(2, userPerson.getLastname());
				stm.setString(3, userPerson.getEmail());
				stm.setString(4, paswwordEncoder.encode(userPerson.getPassword()));
				stm.setDate(5, new java.sql.Date(userPerson.getRegistered().getTime()));
				stm.setInt(6, userPerson.getGender());
				stm.setInt(7, userPerson.getStatus());
				stm.setString(8, userPerson.getPhoto());
				return stm;
			}
		}, generatedKeyHolder);
		
		sql= "INSERT INTO user_role(role, user_person) VALUES (?, ?)";
		jdbcTemplate.update(sql, role, generatedKeyHolder.getKey().longValue());
		
		// No vale por el id xD (por ahora, mejor checar despues xD)
		//jdbcTemplate.update(sql, userPerson.getName(), userPerson.getLastname(), userPerson.getEmail(), userPerson.getPassword(), userPerson.getRegistered(), userPerson.getGender(), userPerson.getStatus(), userPerson.getPhoto());

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
