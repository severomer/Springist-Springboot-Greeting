package com.springist.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="eventname")
	private String event_name;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
	@Column(name="eventdate")
	private Date event_date;

	@Column(name="ownerid")
	private Long userId;
	
	@Column(name="lat")
	private String elat;

	@Column(name="lng")
	private String elng;
	
	@Column(name="isprivate")
	private Boolean ozel;
	
	public Boolean getOzel() {
		return ozel;
	}

	public void setOzel(Boolean ozel) {
		this.ozel = ozel;
	}

	public Event(Long id, String event_name, Date event_date, Long userId, String elat, String elng) {
		this.id = id;
		this.event_name = event_name;
		this.event_date = event_date;
		this.userId = userId;
		this.elat = elat;
		this.elng = elng;
	}

	public String getElat() {
		return elat;
	}

	public void setElat(String elat) {
		this.elat = elat;
	}

	public String getElng() {
		return elng;
	}

	public void setElng(String elng) {
		this.elng = elng;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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
