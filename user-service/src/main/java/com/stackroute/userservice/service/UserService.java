package com.stackroute.userservice.service;

import java.util.List;
import java.util.Optional;

import com.stackroute.userservice.model.User;

public interface UserService {
	
	public void registerUser(User user);
	public boolean validateUser(User user);
	public List<User> getAllUser();
	public User findByEmailId(String emailId);

    String walletId();
    //public String walletId();


}
