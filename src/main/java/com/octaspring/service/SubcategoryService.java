package com.octaspring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Subcategory;

public class SubcategoryService implements SubcategoryInterface{

	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public SubcategoryService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Subcategory subcategory, int category) {
		// TODO Auto-generated method stub
		sql= "INSERT INTO subcategory(name, description, status, category) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, subcategory.getName(), subcategory.getDescription(), subcategory.getStatus(), category);
		
	} 

	@Override
	public void update(Subcategory subcategory, int category) {
		// TODO Auto-generated method stub
		sql= "UPDATE subcategory SET name = ?, description = ?, status = ?, category = ? WHERE id = ?";
		jdbcTemplate.update(sql, subcategory.getName(), subcategory.getDescription(), subcategory.getStatus(), category, subcategory.getId());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql= "DELETE FROM subcategory WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Subcategory> findAll() {
		sql = "SELECT * FROM subcategory INNER JOIN category ON category.id = subcategory.category;";
		List<Subcategory> listSub = this.jdbcTemplate.query(sql, 
			new RowMapper<Subcategory>() {
				@Override
				public Subcategory mapRow(ResultSet rs, int rowNum) throws SQLException {
					Subcategory sc = new Subcategory();
					sc.setId(new Long(rs.getInt("subcategory.id")));
					sc.setName(rs.getString("subcategory.name"));
					sc.setDescription(rs.getString("subcategory.description"));
					sc.setStatus(rs.getInt("subcategory.status"));
						
					Category c = new Category();
					c.setName(rs.getString("category.name"));
					c.setId(new Long(rs.getInt("category.id")));
					sc.setCategory(c);
						
					return sc;
				}
			}
		);
		return listSub;
	}

	@Override
	public Subcategory findById(long id) {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM subcategory INNER JOIN category ON category.id = subcategory.category WHERE subcategory.id = ?";
		Subcategory sc = jdbcTemplate.queryForObject(sql, new Object[] {id},
			new RowMapper<Subcategory>() {

				@Override
				public Subcategory mapRow(ResultSet rs, int rowNum) throws SQLException {
					Subcategory sc = new Subcategory();
					sc.setId(new Long(rs.getInt("subcategory.id")));
					sc.setName(rs.getString("subcategory.name"));
					sc.setDescription(rs.getString("subcategory.description"));
					sc.setStatus(rs.getInt("subcategory.status"));
						
					Category c = new Category();
					c.setName(rs.getString("category.name"));
					c.setId(new Long(rs.getInt("category.id")));
					sc.setCategory(c);
						
					return sc;
				}
			}
		);
		return sc;
	}

}
