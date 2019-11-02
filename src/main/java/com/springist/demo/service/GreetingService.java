package com.springist.demo.service;

import java.util.List;

import com.springist.demo.entity.Greeting;


public interface GreetingService {

	public List<Greeting> findAll();
	
	public String dummy();
	
	public void save(Greeting theGreeting);
}
