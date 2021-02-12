package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.UserPerson;

public interface UserPersonInterface {
	public void save(UserPerson userPerson);
	
	public void update(UserPerson userPerson);
	
	public void delete(long id);
	
	public List<UserPerson> findAll();
	
	public List<UserPerson> findById(long id);
}
