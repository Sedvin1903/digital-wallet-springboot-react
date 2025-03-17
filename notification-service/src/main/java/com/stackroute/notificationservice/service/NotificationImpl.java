package com.stackroute.notificationservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.stackroute.notificationservice.model.User;
import com.stackroute.notificationservice.repository.NotificationRepository;


@Service
public class NotificationImpl implements NotificationService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	


	@Override
	public User sendLoginNotification(String emailId, String password) {
		
		Optional<User> optionalUser = notificationRepository.findByEmailId(emailId);
		
		
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom(fromMail);
		simpleMailMessage.setSubject("Welcome to Digimon - Your Digital Wallet  - Registration successful !! ✅ ");
		simpleMailMessage.setText("Welcome to Digimon!\n Exciting news! Your Digimon digital wallet is now set up and ready to go.\n Here are your account details:\nEmail: " + optionalUser.get().getEmailId() + "\nPassword: " + optionalUser.get().getPassword() + "\n With Digimon, managing your finances, making payments, and tracking your spending is a breeze. Our top-notch security features ensure your money is always safe and secure.\n:woman-raising-hand: Need help or have questions? Reach out to our friendly support team at digimon.walletinfo@gmail.com .\n:star-struck: We can't wait for you to enjoy the benefits of a digital wallet. Let's get started!\nBest,\nThe Digimon Team");
		simpleMailMessage.setTo(emailId);

		mailSender.send(simpleMailMessage);

		return optionalUser.isPresent() ? optionalUser.get() : null;
		
	}
	
}
