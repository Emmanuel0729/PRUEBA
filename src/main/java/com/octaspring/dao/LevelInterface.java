package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Level;

public interface LevelInterface {
	public void save(Level level);
	
	public void update(Level level);
	
	public void delete(long id);
	
	public List<Level> findAll();
	
	public List<Level> findById(long id);
}
