package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course_question")
public class CourseQuestion {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="course")
	private Course course;
	
	@Column(name="owner")
	private UserPerson owner;
	
	@Column(name="question")
	private String question;

	public CourseQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseQuestion(Course course, UserPerson owner, String question) {
		super();
		this.course = course;
		this.owner = owner;
		this.question = question;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public UserPerson getOwner() {
		return owner;
	}

	public void setOwner(UserPerson owner) {
		this.owner = owner;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
