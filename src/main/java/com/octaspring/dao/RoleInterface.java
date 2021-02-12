package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Role;

public interface RoleInterface {
	public void save(Role role);
	
	public void update(Role role);
	
	public void delete(long id);
	
	public List<Role> findAll();
	
	public List<Role> findById(long id);
}
