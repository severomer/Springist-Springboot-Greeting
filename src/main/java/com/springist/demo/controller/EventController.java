package com.springist.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import com.springist.demo.entity.Pager;
import com.springist.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springist.demo.entity.Event;
import com.springist.demo.entity.Greeting;
import com.springist.demo.service.EventService;
import com.springist.demo.service.GreetingService;

@Controller
public class EventController {
	

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};
    
    private static final int INITIAL_USERID = 2;


	private GreetingService greetingService;
    
    private final EventService eventService;

	public EventController(EventService eventService, GreetingService greetingService) {
		this.eventService = eventService;
		this.greetingService = greetingService;
	}
	

    @Autowired
    private JavaMailSender javaMailSender;
    
	 @GetMapping("/events")
	    public ModelAndView showPersonsPage(@RequestParam("eventId") Optional<Integer> eventId,
	    									@RequestParam("pageSize") Optional<Integer> pageSize,
	                                        @RequestParam("page") Optional<Integer> page) {
	        ModelAndView modelAndView = new ModelAndView("events");

	        // Evaluate page size. If requested parameter is null, return initial
	        // page size
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        // Evaluate page. If requested parameter is null or less than 0 (to
	        // prevent exception), return initial size. Otherwise, return value of
	        // param. decreased by 1.
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        
	        int evalEventId = eventId.orElse(INITIAL_USERID);
	        
	        Page<Event> events = eventService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);

	        
			
			List<Greeting> theGreetings;

//			theGreetings = greetingService.findAll();

			theGreetings = greetingService.findByEventId((long)evalEventId);
			
			modelAndView.addObject("greetings", theGreetings);

	        Event newEvent = new Event();
	        modelAndView.addObject("event", newEvent);
	        
	    	
			Greeting theGreeting = new Greeting();
			modelAndView.addObject("greeting", theGreeting);
			
		
	        
	        modelAndView.addObject("events", events);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }

	 @GetMapping("/my")
	    public ModelAndView showMyEventPage(@RequestParam("userId") Optional<Integer> userId,
	    									@RequestParam("pageSize") Optional<Integer> pageSize,
	                                        @RequestParam("page") Optional<Integer> page) {
	        ModelAndView modelAndView = new ModelAndView("events");

	        // Evaluate page size. If requested parameter is null, return initial
	        // page size
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        // Evaluate page. If requested parameter is null or less than 0 (to
	        // prevent exception), return initial size. Otherwise, return value of
	        // param. decreased by 1.
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        
	        int evalUserId = userId.orElse(INITIAL_USERID);
	        
//	        Page<Event> events = eventService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
	        
	        Page<Event> events = eventService.findByUser((long) evalUserId, PageRequest.of(evalPage, evalPageSize));
	        
	        Pager pager = new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);

	        Event newEvent = new Event();
	        modelAndView.addObject("event", newEvent);
	        
	    	
			Greeting theGreeting = new Greeting();
			modelAndView.addObject("greeting", theGreeting);
			
			        
	        modelAndView.addObject("events", events);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	 
	 
		

		@PostMapping("/events/save")
		public String saveEvent(@RequestParam("userId") int theId, @RequestParam("Edate") String Edate,
				@ModelAttribute("event") Event theEvent) {

			System.out.println("inpute" + Edate);
			
			
			
			LocalDateTime localDateTime = LocalDateTime.now();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(Edate, formatter);
			
			System.out.println("formater:" + dateTime.toString() );
		
	//		theEvent.setEvent_date((Date) dateTime);
	//		theEvent.setEvent_date(java.sql.Timestamp.valueOf(localDateTime));
			theEvent.setEvent_date(java.sql.Timestamp.valueOf(dateTime));			
//			User theUser = new User();
			
//			theUser = userService.findByUserId((long)theId);
			
			theEvent.setUserId((long) theId);
		
			
			eventService.save(theEvent);
			

			return "redirect:/home";
					
		}
		
 
		

		@PostMapping("/events/update")
		public String updateEvent(@RequestParam("eventId") int theId, @RequestParam("eLat") String theLat, @RequestParam("eLng") String theLng,  
				@ModelAttribute("event") Event theEvent) {

			theEvent = eventService.findById((long) theId).get();

			theEvent.setElat(theLat);
			theEvent.setElng(theLng);
			eventService.save(theEvent);
			

			return "redirect:/home";
					
		}

		
		@GetMapping("/eventd")
		  public ModelAndView showEventPage(@RequestParam("eventId") Optional<Integer> eventId) {
ModelAndView modelAndView = new ModelAndView("eventd");

int evalEventId = eventId.orElse(INITIAL_USERID);

List<Greeting> theGreetings;

//theGreetings = greetingService.findAll();

theGreetings = greetingService.findByEventId((long)evalEventId);

modelAndView.addObject("greetings", theGreetings);

//Event newEvent = new Event();
Event newEvent =  eventService.findById((long) evalEventId).get();
modelAndView.addObject("event", newEvent);


Greeting theGreeting = new Greeting();
modelAndView.addObject("greeting", theGreeting);



modelAndView.addObject("event", newEvent);

// Float lat = (float) 41.0;
// Float lng = (float) 29.0;

String lat = "39.9"; 
String lng = "32.8";

lat = newEvent.getElat() == null ? lat : newEvent.getElat() ;   //"40.0";
lng = newEvent.getElng() == null ? lng : newEvent.getElng(); //"28.0";

modelAndView.addObject("lat", lat);

modelAndView.addObject("lng", lng);

return modelAndView;
		}
		
		
		//eventdate i eventd.html den Date type ile alamiyoruz. String to Date conversion exception veriyor.
		
		@PostMapping("/eventd/sendemail")
		public String sendMail(@RequestParam("eventname") Optional<String> eventname, @RequestParam("eventdate") String eventdate, @RequestParam("pageaddress") String pageaddress) throws MessagingException, IOException {


	        System.out.println("Sending Email...");

	        sendEmail(eventname, eventdate, pageaddress);
			//sendEmailWithAttachment();

	        System.out.println("Done");

			return "redirect:/home";
					
		}
		// bunun calismasi icin https://myaccount.google.com/lesssecureapps den daha az guvenli uygulamalara izin veri actim
		
		void sendEmail(Optional<String> eventname, String eventdate, String pageaddress) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("severomer86@gmail.com");

	        msg.setSubject("New Test with eventname and date");
	        msg.setText("Hello World \n Spring Boot Email \n" + eventname.get() + "\n" + eventdate.toString() +"\n" + pageaddress.toString());

	        javaMailSender.send(msg);

	    }
}
