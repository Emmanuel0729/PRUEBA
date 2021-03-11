package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.RoleInterface;
import com.octaspring.entity.Role;

public class RoleService implements RoleInterface{
	
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public RoleService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRolesNotAdmin() {
		// TODO Auto-generated method stub
		sql= "SELECT * FROM role WHERE name != 'ROLE_ADMIN'";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Role.class));
	}

}
