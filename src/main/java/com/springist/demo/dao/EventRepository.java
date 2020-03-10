package com.springist.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springist.demo.entity.Event;
import com.springist.demo.entity.EventUser;
import com.springist.demo.entity.User;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

	Page<Event> findByUserId(Long userId, Pageable pageable);

	Page<Event> findByOzelFalse(Pageable pageable);

	Page<Event> findByMembers(User eUser, Pageable pageable);

	Page<Event> findByEventUsers(EventUser eventUser, Pageable pageable);

	Page<Event> findByIdIn(List<Long> findByUser, Pageable pageable);

}
