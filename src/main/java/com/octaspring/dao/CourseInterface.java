package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Course;


public interface CourseInterface {
	public void save(Course course);
	
	public void update(Course course);
	
	public void delete(long id);
	
	public List<Course> findAll();
	
	public List<Course> findById(long id);
}
