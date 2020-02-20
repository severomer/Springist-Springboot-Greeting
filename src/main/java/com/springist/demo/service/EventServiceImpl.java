package com.springist.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springist.demo.dao.EventRepository;
import com.springist.demo.entity.Event;

@Service
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;
	
	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public Page<Event> findAllPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return eventRepository.findAll(pageable);
	}

	@Override
	public Page<Event> findByUser(Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return eventRepository.findByUserId(userId, pageable);
	}

	@Override
	public void save(Event theEvent) {
		// TODO Auto-generated method stub
		eventRepository.save(theEvent);
	}

	@Override
	public Optional<Event> findById(Long eventId) {
		// TODO Auto-generated method stub
		return eventRepository.findById(eventId);
	}

	@Override
	public Page<Event> findByOzelFalse(Pageable pageable) {
		// TODO Auto-generated method stub
		return eventRepository.findByOzelFalse(pageable);
	}

}
