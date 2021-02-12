package com.octaspring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase { 
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="user_person")
	private UserPerson user_person;
	
	@Column(name="registered")
	private Date registered;
	
	@Column(name="total")
	private double total;

	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Purchase(UserPerson user_person, Date registered, double total) {
		super();
		this.user_person = user_person;
		this.registered = registered;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserPerson getUser_person() {
		return user_person;
	}

	public void setUser_person(UserPerson user_person) {
		this.user_person = user_person;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
