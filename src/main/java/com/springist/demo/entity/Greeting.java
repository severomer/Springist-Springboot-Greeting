package com.springist.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name = "greeting")
@JsonFilter("userFilter")
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="message")
	private String message;
	
	@Column(name="postdate")
	private Date post_date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	
	public Greeting(String message, Date post_date, User user) {

		this.message = message;
		this.post_date = post_date;
		this.user= user;
	}
	
	public Greeting(String message, Date post_date) {

		this.message = message;
		this.post_date = post_date;
	}

	public Greeting(String message) {

		this.message = message;
	}
	
	public Greeting() {
		
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", message=" + message + ", post_date=" + post_date + ", user=" + user + "]";
	}
	
	
}
