package com.springist.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springist.demo.entity.Event;
import com.springist.demo.entity.Greeting;
import com.springist.demo.service.EventService;
import com.springist.demo.service.GreetingService;

@RestController
@RequestMapping("/api")
public class GreetingRestController {

    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

	private GreetingService greetingService;
	
	private EventService eventService;
	
	
	@Autowired
	public GreetingRestController(GreetingService theGreetingService, EventService eventService) {
		greetingService = theGreetingService;
		this.eventService = eventService;
	}
	
	
	@GetMapping("/greetings")
	public MappingJacksonValue listAll(){
		
		List<Greeting> greetings = greetingService.findAll();
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(greetings);
		
		FilterProvider filters = new SimpleFilterProvider()
				.setFailOnUnknownId(false)
                .addFilter("userFilter", SimpleBeanPropertyFilter
                        .filterOutAllExcept("message","post_date","user.userName"));  //nested filter not suppoerted userName notworking
    mappingJacksonValue.setFilters(filters);
    return mappingJacksonValue;
		
		
		
//		return greetingService.findAll();
		
	}
	
	
	@GetMapping("/greetings/{userName}")
	
	public MappingJacksonValue listUser(@PathVariable String userName){
		
		List<Greeting> greetings = greetingService.findByName(userName);
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(greetings);
		
		FilterProvider filters = new SimpleFilterProvider()
				.setFailOnUnknownId(false)
                .addFilter("userFilter", SimpleBeanPropertyFilter
                        .serializeAll()
                        );  //nested filter not suppoerted userName notworking
    mappingJacksonValue.setFilters(filters);
    return mappingJacksonValue;

//		return greetings;
	
		
	}
	
	@GetMapping("/events/{pageSize}/{page}")
	public Page<Event> listEvents(@PathVariable Optional<Integer> pageSize, 
									@PathVariable Optional<Integer> page) {
		

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

  //      int evalPage = INITIAL_PAGE;
		Page<Event> events =  eventService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		return events;
	}
	
	
	
	@GetMapping("/events")
	public Page<Event> listFirstEvents() {
		

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = 10;
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
 //       int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        int evalPage = INITIAL_PAGE;
		Page<Event> events =  eventService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		return events;
	}
	
	
/*
	@RequestMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		System.out.println("name" + principal.getName());	
		System.out.println("id" + principal.getAttribute("id").toString());	
		return Collections.singletonMap("login", principal.getAttribute("login"));
	}

*/
}
