package com.stackroute.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.stackroute.userservice.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void registerUser(User user) {
		// TODO Auto-generated method stub
		try {
			checkUser(user);
			user.setWalletId((long) (Math.random()*1000000000));
			userRepository.save(user);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, user);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.NOTIFICATION_ROUTING_KEY, user);
			rabbitTemplate.convertAndSend(RabbitMqConfig.WALLET_EXCHANGE, RabbitMqConfig.WALLET_ROUTING_KEY, user);
			System.out.println("USER REGISTERED : "+ user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean validateUser(User user) {
		return  userRepository.existsByEmailIdAndPassword(user.getEmailId(), user.getPassword());
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findByEmailId(String emailId) {
		Optional<User>  optional= userRepository.findByEmailId(emailId);
		return optional.orElse(null);
	}


	@Override
	public String walletId() {
		return "";
	}

	private void checkUser(User user) throws Exception {
		if((user.getMobileNumber().length())!=10){
			throw new Exception("Mobile Number invalid "+ user.getMobileNumber());
		}
		boolean isMatch = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$").matcher(user.getPassword()).matches();
		if(isMatch){
			System.out.println("PASSWORD matcher : "+ isMatch);
		}
		else {
			throw new Exception("password not complex enough ");
		}
	}


	}