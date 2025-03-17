package com.stackroute.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	boolean existsByEmailIdAndPassword(String emailId, String password);
	Optional<User>  findByEmailId(String emailId);
}
