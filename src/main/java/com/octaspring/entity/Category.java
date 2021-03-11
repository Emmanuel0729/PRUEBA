package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(name="id")
	private Long id;
	
	@NotBlank(message="Pon un nombre bro")
	@Size(min=3, max=50)
	@Column(name="name")
	private String name;
	
	@NotBlank(message="Pon una descripcion bro")
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="status")
	private int status;
	
	public Category() {
		super();
	}
	
	public Category(String name, String description, String image, int status) {
		super();
		this.name= name;
		this.description = description;
		this.image = image;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
