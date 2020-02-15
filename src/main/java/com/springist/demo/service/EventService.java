package com.springist.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springist.demo.entity.Event;

public interface EventService {

	  Page<Event> findAllPageable(Pageable pageable);

	Page<Event> findByUser(Long evalUserId, Pageable pageable);

	void save(Event theEvent);

	Optional<Event> findById(Long eventId);
}
