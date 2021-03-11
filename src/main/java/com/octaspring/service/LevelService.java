package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.LevelInterface;
import com.octaspring.entity.Level;

public class LevelService implements LevelInterface{

	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public LevelService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Level level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Level level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Level> findAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM level";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Level.class));
	}

	@Override
	public List<Level> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
