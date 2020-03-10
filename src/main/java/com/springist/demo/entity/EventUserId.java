package com.springist.demo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class EventUserId implements Serializable {
	
	private Event event;
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
