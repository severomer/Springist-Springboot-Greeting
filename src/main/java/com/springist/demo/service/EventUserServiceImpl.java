package com.springist.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springist.demo.dao.EventUserRepository;
import com.springist.demo.entity.Event;
import com.springist.demo.entity.EventUser;
import com.springist.demo.entity.User;

@Service
public class EventUserServiceImpl implements EventUserService {

	public final EventUserRepository eventUserRepository;

	public EventUserServiceImpl(EventUserRepository eventUserRepository) {
		this.eventUserRepository = eventUserRepository;
	}

	@Override
	public void save(EventUser eventUser) {
		eventUserRepository.save(eventUser);
		
	}

	@Override
	public List<Long> findByUser(User eUser) {
		// TODO Auto-generated method stub
		return eventUserRepository.findByUser(eUser);
	}

	@Override
	public List<Event> findByUserId(long evalUserId) {
		// TODO Auto-generated method stub
		return eventUserRepository.findByUserId(evalUserId);
	}

	@Override
	public List<EventUser> findByPrimaryKey(Event newEvent) {
		// TODO Auto-generated method stub
		return eventUserRepository.findByPrimaryKeyEvent(newEvent);
	}

	@Override
	public EventUser findByIds(long theId, long userId) {
		// TODO Auto-generated method stub
		return eventUserRepository.findByIds(theId, userId);
	}
	
	
}
