package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Subcategory;

public interface SubcategoryInterface {
	public void save(Subcategory subcategory, int category);
	
	public void update(Subcategory subcategory, int category);
	
	public void delete(long id);
	
	public List<Subcategory> findAll();
	
	public Subcategory findById(long id);
}
