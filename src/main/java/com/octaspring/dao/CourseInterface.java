package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Course;


public interface CourseInterface {
	public void save(Course course, int category, int subcategory, int level, int lang);
	
	public void update(Course course, int category, int subcategory, int level, int lang);
	
	public void delete(long id);
	
	public void deleteLogical(long id, int status);
	
	public List<Course> findAll();
	
	public Course findById(long id);
}
