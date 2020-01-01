package com.springist.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springist.demo.dao.GreetingRepository;
import com.springist.demo.entity.Greeting;

@Service
public class GreetingServiceImpl implements GreetingService {

	private GreetingRepository greetingRepository;
	
	@Autowired
	public GreetingServiceImpl (GreetingRepository theGreetingRepository) {

		System.out.println("Buraya greeting Service Imple constructor geldi");
		
		greetingRepository=theGreetingRepository;
	}
	
	
	@Override
	@Transactional
	public List<Greeting> findAll() {

		System.out.println("Buraya greeting Service Imple icine geldi");
		
		return greetingRepository.findAll();
	}


	@Override
	public String dummy() {
		// TODO Auto-generated method stub
		return "Bakalim napicak";
	}


	@Override
	@Transactional
	public void save(Greeting theGreeting) {
		// TODO Auto-generated method stub
		greetingRepository.save(theGreeting);
	}


	@Override
	public List<Greeting> findByValue(String theValue) {
		// TODO Auto-generated method stub
		return greetingRepository.findByValue(theValue);
	}


	@Override
	public List<Greeting> findByName(String userName) {
		// TODO Auto-generated method stub
		return greetingRepository.findByName(userName);
	}


	@Override
	public Greeting findById(Long theId) {
		// TODO Auto-generated method stub
		return greetingRepository.findById(theId);
	}

}
