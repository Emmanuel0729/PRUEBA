package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Subcategory;

public interface SubcategoryInterface {
	public void save(Subcategory subcategory);
	
	public void update(Subcategory subcategory);
	
	public void delete(long id);
	
	public List<Subcategory> findAll();
	
	public List<Subcategory> findById(long id);
}
