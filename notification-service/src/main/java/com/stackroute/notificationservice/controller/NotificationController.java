package com.stackroute.notificationservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.notificationservice.model.User;
import com.stackroute.notificationservice.service.*;

import java.util.Map;
import java.util.HashMap;


@RestController
@CrossOrigin
@RequestMapping("/digimon/v1")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/")
	public ResponseEntity<String> check()
	{
		ResponseEntity<String> entity = new ResponseEntity<String>("OK its working",HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/success")
	public ResponseEntity< User>  loginNotification(@RequestBody User user)
	{
		User u = notificationService.sendLoginNotification(user.getEmailId(), user.getPassword());
		
		ResponseEntity<User> entity = new ResponseEntity<User>(u,HttpStatus.FOUND);
		return entity;
		// ResponseEntity<Map<String, User>> 
//		  Map<String, User> response = new HashMap<>();
//		    response.put("data", u);
//
//		    ResponseEntity<Map<String, User>> entity = new ResponseEntity<>(response, HttpStatus.FOUND);
//		    return entity;
	}

}
