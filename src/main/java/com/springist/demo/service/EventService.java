package com.springist.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springist.demo.entity.Event;
import com.springist.demo.entity.EventUser;
import com.springist.demo.entity.User;

public interface EventService {

	  Page<Event> findAllPageable(Pageable pageable);

	Page<Event> findByUser(Long evalUserId, Pageable pageable);

	void save(Event theEvent);

	Optional<Event> findById(Long eventId);

	Page<Event> findByOzelFalse(Pageable pageable);

	Page<Event> findByMembers(User eUser, Pageable pageable);

	Page<Event> findByEventUsers(EventUser eventUser, Pageable pageable);

	Page<Event> findByIdIn(List<Long> findByUser, Pageable pageable);
}
