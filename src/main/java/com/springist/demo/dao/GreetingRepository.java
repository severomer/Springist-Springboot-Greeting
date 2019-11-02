package com.springist.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.springist.demo.entity.Greeting;
import com.springist.demo.entity.User;

@Repository
public class GreetingRepository implements GreetingDao {


	@Autowired
	private EntityManager entityManager;
	
	

	public List<Greeting> findAll() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		System.out.println("Buraya Query oncesi geldi");
		
		// now retrieve/read from database using username
		Query<Greeting> theQuery = currentSession.createQuery("from Greeting", Greeting.class);
		
		System.out.println("Buraya Query icine geldi");
		
		List<Greeting> theGreetings;
		
		try {
			theGreetings = theQuery.getResultList();
		} catch (Exception e) {
			theGreetings = null;
		}


		return theGreetings;
	}



	public void save(Greeting theGreeting) {
		// get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);

				currentSession.saveOrUpdate(theGreeting);
	}

}
