package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course_question_answer")
public class CourseQuestionAnswer {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="question")
	private CourseQuestion question;
	
	@Column(name="user_person")
	private UserPerson user_person;
	
	@Column(name="answer")
	private String answer;

	public CourseQuestionAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseQuestionAnswer(CourseQuestion question, UserPerson user_person, String answer) {
		super();
		this.question = question;
		this.user_person = user_person;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CourseQuestion getQuestion() {
		return question;
	}

	public void setQuestion(CourseQuestion question) {
		this.question = question;
	}

	public UserPerson getUser_person() {
		return user_person;
	}

	public void setUser_person(UserPerson user_person) {
		this.user_person = user_person;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
