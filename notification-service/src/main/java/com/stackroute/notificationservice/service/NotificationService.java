package com.stackroute.notificationservice.service;

import com.stackroute.notificationservice.model.User;
import com.stackroute.notificationservice.repository.NotificationRepository;

public interface NotificationService {
	
	public User sendLoginNotification(String emailId , String password);

}
