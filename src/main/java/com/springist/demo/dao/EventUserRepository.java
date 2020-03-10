package com.springist.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springist.demo.entity.Event;
import com.springist.demo.entity.EventUser;
import com.springist.demo.entity.EventUserId;
import com.springist.demo.entity.User;

public interface EventUserRepository extends JpaRepository<EventUser, EventUserId> {


	@Query("SELECT e.primaryKey.event.id FROM EventUser e WHERE e.primaryKey.user = ?1")
	List<Long> findByUser(User eUser);

	@Query("SELECT e.primaryKey.event FROM EventUser e WHERE e.primaryKey.user.id = ?1")
	List<Event> findByUserId(long evalUserId);
	
	List<Event> findByAttended(Boolean attend);

	List<Event> findByPrimaryKeyUser(User eUser);
	
	List<EventUser> findByPrimaryKeyEvent(Event event);

	@Query("SELECT e FROM EventUser e WHERE e.primaryKey.user.id = ?2 AND e.primaryKey.event.id = ?1")
	EventUser findByIds(long theId, long userId);
}
