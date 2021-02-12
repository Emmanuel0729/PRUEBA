package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_course")
public class PurchaseCourse {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="purchase")
	private Purchase purchase;
	
	@Column(name="course")
	private Course course;
	
	@Column(name="total")
	private double total;

	public PurchaseCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseCourse(Purchase purchase, Course course, double total) {
		super();
		this.purchase = purchase;
		this.course = course;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
