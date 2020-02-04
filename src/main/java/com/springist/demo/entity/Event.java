package com.springist.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="eventname")
	private String event_name;
	
	@Column(name="eventdate")
	private Date event_date;

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}

	public Long getId() {
		return id;
	}

	public Event() {
		
	}
	public Event(String event_name, Date event_date) {

		this.event_name = event_name;
		this.event_date = event_date;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", event_name=" + event_name + ", event_date=" + event_date + "]";
	}

	
}
