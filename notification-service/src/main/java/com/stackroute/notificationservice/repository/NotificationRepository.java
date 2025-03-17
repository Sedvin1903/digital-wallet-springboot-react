package com.stackroute.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.notificationservice.model.User;
import java.util.Optional;


@Repository
public interface NotificationRepository extends JpaRepository<User, Integer>  {

	public Optional<User> findByEmailId(String emailId);
	
}
