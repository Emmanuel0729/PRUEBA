package com.octaspring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.octaspring.dao.CourseInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Course;
import com.octaspring.entity.Lang;
import com.octaspring.entity.Level;
import com.octaspring.entity.Subcategory;
import com.octaspring.entity.UserPerson;

public class CourseService implements CourseInterface{
	
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	public CourseService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Course course, int category, int subcategory, int level, int lang) {
		// TODO Auto-generated method stub
		sql= "INSERT INTO course(title, subtitle, description, price, owner, category, subcategory, status, level, published, lang, image) values(?, ?, ?, ?, 8, ?, ?, ?, ?, ?, ?, ?)";
		course.setPublished(new Date());
		jdbcTemplate.update(sql, course.getTitle(), course.getSubtitle(), course.getDescription(), course.getPrice(), category, subcategory, course.getStatus(), level, course.getPublished(), lang, course.getImage());
	}

	@Override
	public void update(Course course, int category, int subcategory, int level, int lang) {
		// TODO Auto-generated method stub
		sql= "UPDATE course SET title = ?, subtitle = ?, description = ?, price = ?, owner = 8, category = ?, subcategory = ?, status = ?, level = ?, lang = ?, image = ? WHERE id = ?";
		jdbcTemplate.update(sql, course.getTitle(), course.getSubtitle(), course.getDescription(), course.getPrice(), category, subcategory, course.getStatus(), level, lang, course.getImage(), course.getId());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql= "DELETE FROM course WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM course "+
				"INNER JOIN category ON category.id = course.category "+
				"INNER JOIN subcategory ON subcategory.id = course.subcategory "+
				"INNER JOIN level ON level.id = course.level "+
				"INNER JOIN lang ON lang.id = course.lang "+
				"INNER JOIN user_person ON user_person.id = course.owner";
		List<Course> listCourse = this.jdbcTemplate.query(sql, 
			new RowMapper<Course>() {

				@Override
				public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Course c = new Course();
					c.setId(new Long(rs.getInt("course.id")));
					c.setTitle(rs.getString("course.title"));
					c.setSubtitle(rs.getString("course.subtitle"));
					c.setDescription(rs.getString("course.description"));
					c.setPrice(rs.getDouble("course.price"));
					c.setStatus(rs.getInt("course.status"));
					c.setPublished(rs.getDate("course.published"));
					c.setImage(rs.getString("course.image"));
					
					Category ca = new Category();
					ca.setName(rs.getString("category.name"));
					ca.setId(new Long(rs.getInt("category.id")));
					c.setCategory(ca);
					
					Subcategory s = new Subcategory();
					s.setName(rs.getString("subcategory.name"));
					s.setId(new Long(rs.getInt("subcategory.id")));
					c.setSubcategory(s);
					
					UserPerson u = new UserPerson();
					u.setEmail(rs.getString("user_person.email"));
					u.setId(new Long(rs.getInt("user_person.id")));
					c.setOwner(u);
					
					Level le = new Level();
					le.setLevel(rs.getString("level.level"));
					le.setId(new Long(rs.getInt("level.id")));
					c.setLevel(le);
					
					Lang la = new Lang();
					la.setLang(rs.getString("lang.lang"));
					la.setId(new Long(rs.getInt("lang.id")));
					c.setLang(la);
					return c;
				}
			
			}
		);
		return listCourse;
	}

	@Override
	public Course findById(long id) {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM course INNER JOIN category ON category.id = course.category INNER JOIN subcategory ON subcategory.id = course.subcategory INNER JOIN level ON level.id = course.level INNER JOIN lang ON lang.id = course.lang INNER JOIN user_person ON user_person.id = course.owner WHERE course.id = ?";
		Course co = jdbcTemplate.queryForObject(sql, new Object[] {id},
			new RowMapper<Course>() {

				@Override
				public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Course c = new Course();
					c.setId(new Long(rs.getInt("course.id")));
					c.setTitle(rs.getString("course.title"));
					c.setSubtitle(rs.getString("course.subtitle"));
					c.setDescription(rs.getString("course.description"));
					c.setPrice(rs.getDouble("course.price"));
					c.setStatus(rs.getInt("course.status"));
					c.setPublished(rs.getDate("course.published"));
					c.setImage(rs.getString("course.image"));

					Category ca = new Category();
					ca.setName(rs.getString("category.name"));
					ca.setId(new Long(rs.getInt("category.id")));
					c.setCategory(ca);

					Subcategory s = new Subcategory();
					s.setName(rs.getString("subcategory.name"));
					s.setId(new Long(rs.getInt("subcategory.id")));
					c.setSubcategory(s);

					UserPerson u = new UserPerson();
					u.setName(rs.getString("user_person.name"));
					u.setLastname(rs.getString("user_person.lastname"));
					u.setId(new Long(rs.getInt("user_person.id")));
					c.setOwner(u);

					Level le = new Level();
					le.setLevel(rs.getString("level.level"));
					le.setId(new Long(rs.getInt("level.id")));
					c.setLevel(le);

					Lang la = new Lang();
					la.setLang(rs.getString("lang.lang"));
					la.setId(new Long(rs.getInt("lang.id")));
					c.setLang(la);
					return c;
				}

			}
		);
		return co;
	}

	@Override
	public void deleteLogical(long id, int status) {
		// TODO Auto-generated method stub
		sql = "UPDATE course SET status = ? WHERE id = ?";
		jdbcTemplate.update(sql, status, id);
		
	}
}
