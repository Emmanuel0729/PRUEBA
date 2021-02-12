package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.UserHasCourse;

public interface UserHasCourseInterface {
	public void save(UserHasCourse userHasCourse);
	
	public void update(UserHasCourse userHasCourse);
	
	public void delete(long id);
	
	public List<UserHasCourse> findAll();
	
	public List<UserHasCourse> findById(long id);
}
