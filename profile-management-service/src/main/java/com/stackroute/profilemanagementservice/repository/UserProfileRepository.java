package com.stackroute.profilemanagementservice.repository;

import com.stackroute.profilemanagementservice.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

/*
created the repository for service
 */


public interface UserProfileRepository extends MongoRepository<UserProfile, Integer> {

        Optional<UserProfile> findByFirstName(String firstName);
}
