package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.CourseInterface;
import com.octaspring.entity.Course;

public class CourseService implements CourseInterface{
	
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public CourseService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub
		sql= "INSERT INTO course(title, subtitle, description, price, owner, category, subcategory, status, level, published, lang, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, course.getTitle(), course.getSubtitle(), course.getDescription(), course.getPrice(), course.getOwner(), course.getCategory(), course.getSubcategory(), course.getStatus(), course.getLevel(), course.getPublished(), course.getLang(), course.getImage());
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		sql= "UPDATE course SET title = ?, subtitle = ?, description = ?, price = ?, owner = ?, category = ?, subcategory = ?, status = ?, level = ?, published= ?, lang = ?, image = ? WHERE id = ?";
		jdbcTemplate.update(sql, course.getTitle(), course.getSubtitle(), course.getDescription(), course.getPrice(), course.getOwner(), course.getCategory(), course.getSubcategory(), course.getStatus(), course.getLevel(), course.getPublished(), course.getLang(), course.getImage(), course.getId());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql= "DELETE FROM course WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM course";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Course.class));
	}

	@Override
	public List<Course> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
