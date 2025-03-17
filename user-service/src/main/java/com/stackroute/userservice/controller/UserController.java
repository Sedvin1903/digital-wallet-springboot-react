package com.stackroute.userservice.controller;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("digimon/v1")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public ResponseEntity<?> getAllUser() {
		List<User> userList = userService.getAllUser();
		ResponseEntity<List<User>> entity =
				new ResponseEntity<List<User>>(userList,HttpStatus.OK);
		return entity;
	}



	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.registerUser(user);
			String uri = "http://localhost:5001/digimon/v1/success";
			RestTemplate restTemplate = new RestTemplate();
			User user1 = restTemplate.postForObject(uri,user,User.class);
			return new ResponseEntity<>("USER REGISTERED : " + user.getFirstName() + " " + user.getLastName() + " wallet id :" + user.getWalletId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("UNABLE TO REGISTER USER " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

//=======
//		User createdUser=userService.registerUser(user);
//		rabbitTemplate.convertAndSend(RabbitMqConfig.exchange,RabbitMqConfig.routingKey,user);
//		ResponseEntity<User> entity = new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//		return entity;
//>>>>>>> e05cd25 (implemented receiver end in rabbitmq)
	}
	//---------------------------------------------------------------------------------------------------------------------------------//
	//generating JWT here itself
	private String getToken(long userId,long walletId ) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId",userId);
		jsonObject.put("walletId",walletId);
		String token = Jwts.builder()
				.setPayload(jsonObject.toString()) // Set the payload
				.signWith(SignatureAlgorithm.HS256, "yourSecretKey") // Sign with HMACSHA256
				.compact();
		return token;
	}

	@Data
	class UserTokenAndId{
		private long userId;
		private long walletId;
		private String token;
	}

	//using the getToken thingy here
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody User user) {
		if (userService.validateUser(user)) {
			User userForEmailId = userService.findByEmailId(user.getEmailId());
			String token = getToken(userForEmailId.getUserId(),userForEmailId.getWalletId());
			UserTokenAndId userTokenAndId = new UserTokenAndId();
			userTokenAndId.setUserId(userForEmailId.getUserId());
			userTokenAndId.setToken(token);
			userTokenAndId.setWalletId(userForEmailId.getWalletId());
			return new ResponseEntity<>(userTokenAndId,HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid UserName/Password", HttpStatus.NOT_FOUND);
		}
	}
//	@PostMapping("/publish")
//	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
//		userService.sendMessage(message);
//		return ResponseEntity.ok("message sent to rabbitmq");
//
//	}
}
//<<<<<<< HEAD
//=======
//
////	@RabbitListener(queues = RabbitMqConfig.queue)
////	public void listener(UserController userController){
////		System.out.println(userController);
////	}
//
//
//>>>>>>> e05cd25 (implemented receiver end in rabbitmq)
//}
