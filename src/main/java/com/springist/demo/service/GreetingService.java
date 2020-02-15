package com.springist.demo.service;

import java.util.List;

import com.springist.demo.entity.Greeting;


public interface GreetingService {

	public List<Greeting> findAll();
	
	public String dummy();
	
	public void save(Greeting theGreeting);

	public List<Greeting> findByValue(String theValue);

	public List<Greeting> findByName(String userName);

	public Greeting findById(Long theId);

	public List<Greeting> findByEventId(Long id);
}
