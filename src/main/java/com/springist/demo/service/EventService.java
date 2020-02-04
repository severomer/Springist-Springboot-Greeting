package com.springist.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springist.demo.entity.Event;

public interface EventService {

	  Page<Event> findAllPageable(Pageable pageable);
}
