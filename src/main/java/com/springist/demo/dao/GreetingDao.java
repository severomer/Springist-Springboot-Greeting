package com.springist.demo.dao;

import java.util.List;

import com.springist.demo.entity.Greeting;

public interface GreetingDao {

	public List<Greeting> findAll();
	
	public void save(Greeting theGreeting);
}
