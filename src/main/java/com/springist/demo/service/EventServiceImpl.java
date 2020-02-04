package com.springist.demo.service;

import org.springframework.data.domain.Page;
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

}
