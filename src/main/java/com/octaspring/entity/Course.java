package com.octaspring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="subtitle")
	private String subtitle;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private double price;
	
	@Column(name="owner")
	private UserPerson owner;
	
	@Column(name="category")
	private Category category;
	
	@Column(name="subcategory")
	private Subcategory subcategory;
	
	@Column(name="status")
	private int status;
	
	@Column(name="level")
	private Level level;
	
	@Column(name="published")
	private Date published;
	
	@Column(name="lang")
	private Lang lang;
	
	@Column(name="image")
	private String image;
	
	public Course() {
		super();
	}

	public Course(String title, String subtitle, String description, double price, UserPerson owner, Category category,
			Subcategory subcategory, int status, Level level, Date published, Lang lang, String image) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.price = price;
		this.owner = owner;
		this.category = category;
		this.subcategory = subcategory;
		this.status = status;
		this.level = level;
		this.published = published;
		this.lang = lang;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public UserPerson getOwner() {
		return owner;
	}

	public void setOwner(UserPerson owner) {
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Lang getLang() {
		return lang;
	}

	public void setLang(Lang lang) {
		this.lang = lang;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
}