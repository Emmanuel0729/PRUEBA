package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Category;


public interface CategoryInterface {
	public void save(Category category);
	
	public void update(Category category);
	
	public void delete(long id);
	
	public List<Category> findAll();
	
	public Category findById(long id);
}
