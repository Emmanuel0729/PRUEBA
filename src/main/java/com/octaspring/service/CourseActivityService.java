package com.octaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.octaspring.dao.CourseActivityInterface;
import com.octaspring.entity.CourseActivity;

public class CourseActivityService implements CourseActivityInterface{
	
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public CourseActivityService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(CourseActivity courseActivity) {
		// TODO Auto-generated method stub
		sql= "INSERT INTO course_activity(title, description, duration, file, video, course) values(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, courseActivity.getTitle(), courseActivity.getDescription(), courseActivity.getDuration(), courseActivity.getFile(), courseActivity.getVideo(), courseActivity.getCourse());
	}

	@Override
	public void update(CourseActivity courseActivity) {
		// TODO Auto-generated method stub
		sql= "UPDATE course_activity SET title = ?, description = ?, duration = ?, file = ?, video = ?, course = ? WHERE id = ?";
		jdbcTemplate.update(sql, courseActivity.getTitle(), courseActivity.getDescription(), courseActivity.getDuration(), courseActivity.getFile(), courseActivity.getVideo(), courseActivity.getCourse(), courseActivity.getId());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql= "DELETE FROM course_activity WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<CourseActivity> findAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM course_activity";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CourseActivity.class));
	}

	@Override
	public List<CourseActivity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
