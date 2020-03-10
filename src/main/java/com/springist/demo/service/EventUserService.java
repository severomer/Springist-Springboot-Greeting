package com.springist.demo.service;

import com.springist.demo.entity.Event;
import com.springist.demo.entity.EventUser;
import com.springist.demo.entity.User;
import java.util.List;

public interface EventUserService {

	void save(EventUser eventUser);

	List<Long> findByUser(User eUser);

	List<Event> findByUserId(long evalUserId);

	List<EventUser> findByPrimaryKey(Event newEvent);

	EventUser findByIds(long theId, long userId);

}
