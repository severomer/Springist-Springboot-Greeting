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
		Query<Greeting> theQuery = currentSession.createQuery("from Greeting order by post_date desc", Greeting.class);
		
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



	public List<Greeting> findByValue(String theValue) {
		// TODO Auto-generated method stub
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		System.out.println("findByValue Query oncesi geldi " + theValue);
		
		// now retrieve/read from database using username
		Query<Greeting> theQuery = currentSession.createQuery("from Greeting where message like : smessage", Greeting.class);
		
		theQuery.setParameter("smessage", "%" + theValue + "%");
		
		System.out.println("findByValue Query icine geldi " + theValue);
		
		List<Greeting> theGreetings;
		
		try {
			theGreetings = theQuery.getResultList();
		} catch (Exception e) {
			theGreetings = null;
		}


		return theGreetings;	}



	public List<Greeting> findByName(String userName) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);

		System.out.println("findByName Query oncesi geldi " + userName);
		
		// now retrieve/read from database using username
		Query<Greeting> theQuery = currentSession.createQuery("select g from Greeting g join fetch g.user where g.user.userName =: sUser", Greeting.class);
		
		theQuery.setParameter("sUser",  userName );
		
		System.out.println("findByName Query icine geldi " + userName);
		
		List<Greeting> theGreetings;
		
		try {
			theGreetings = theQuery.getResultList();
		} catch (Exception e) {
			theGreetings = null;
		}


		return theGreetings;	}



	public Greeting findById(Long theId) {

		Session currentSession = entityManager.unwrap(Session.class);

		System.out.println("findByID Query oncesi geldi " + theId);
		
		// now retrieve/read from database using username
		Query<Greeting> theQuery = currentSession.createQuery("from Greeting where id =: sID", Greeting.class);
		
		theQuery.setParameter("sID", theId);
		
		System.out.println("findByıd Query icine geldi " + theId);
		
		Greeting theGreeting;
		
		try {
			theGreeting = theQuery.getSingleResult();
		} catch (Exception e) {
			theGreeting = null;
		}


		return theGreeting;	}



	public List<Greeting> findByEventId(Long id) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);

		System.out.println("findByID Query oncesi geldi " + id);
		
		// now retrieve/read from database using username
		Query<Greeting> theQuery = currentSession.createQuery("from Greeting where eventid =: sID", Greeting.class);
		
		theQuery.setParameter("sID", id);
		
		System.out.println("findByıd Query icine geldi " + id);
		
		List<Greeting> theGreetings;
		
		try {
			theGreetings = theQuery.getResultList();
		} catch (Exception e) {
			theGreetings = null;
		}


		return theGreetings;	}


}
