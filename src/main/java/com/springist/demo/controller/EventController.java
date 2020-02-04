package com.springist.demo.controller;

import java.util.Optional;

import com.springist.demo.entity.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springist.demo.entity.Event;
import com.springist.demo.service.EventService;

@Controller
public class EventController {
	

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    private final EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
    
	 @GetMapping("/events")
	    public ModelAndView showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	                                        @RequestParam("page") Optional<Integer> page) {
	        ModelAndView modelAndView = new ModelAndView("events");

	        // Evaluate page size. If requested parameter is null, return initial
	        // page size
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        // Evaluate page. If requested parameter is null or less than 0 (to
	        // prevent exception), return initial size. Otherwise, return value of
	        // param. decreased by 1.
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        
	        Page<Event> events = eventService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);

	        modelAndView.addObject("events", events);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }

}
