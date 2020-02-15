package com.springist.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springist.demo.entity.Event;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

	Page<Event> findByUserId(Long userId, Pageable pageable);

}
