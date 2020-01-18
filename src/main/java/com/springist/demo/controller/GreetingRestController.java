package com.springist.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springist.demo.entity.Greeting;
import com.springist.demo.service.GreetingService;

@RestController
@RequestMapping("/api")
public class GreetingRestController {


	private GreetingService greetingService;
	
	
	
	
	@Autowired
	public GreetingRestController(GreetingService theGreetingService) {
		greetingService = theGreetingService;
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
	
}
