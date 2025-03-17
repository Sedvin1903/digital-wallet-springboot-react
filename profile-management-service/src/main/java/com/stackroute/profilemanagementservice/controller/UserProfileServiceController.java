package com.stackroute.profilemanagementservice.controller;


import com.stackroute.profilemanagementservice.exceptions.UserProfileAlreadyExistsException;
import com.stackroute.profilemanagementservice.exceptions.UserProfileNotExistsException;
import com.stackroute.profilemanagementservice.model.UserProfile;
import com.stackroute.profilemanagementservice.service.UserProfileService;
import com.stackroute.profilemanagementservice.service.UserProfileServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/digimon/v1")
public class UserProfileServiceController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){

        List<UserProfile> userList = userProfileService.getAllUserProfiles();
        ResponseEntity<List<UserProfile>> entity = new ResponseEntity<List<UserProfile>>(userList, HttpStatus.OK);
        return entity;
    }

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody UserProfile user){
        ResponseEntity<?> entity = null;
        try {
            UserProfile createdUserprofile = userProfileService.registerUser(user);
            entity= new ResponseEntity<UserProfile>(createdUserprofile,HttpStatus.CREATED);
        }
        catch(UserProfileAlreadyExistsException e) {
            entity= new ResponseEntity<String>("The userprofile already exists .. "+e.getMessage(),HttpStatus.CONFLICT);
        }

        return entity;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfileById(@PathVariable ("userId") int userId){
        ResponseEntity<?> entity = null;
        try {
            UserProfile createdUserprofile = userProfileService.getUserProfile(userId);
            entity= new ResponseEntity<UserProfile>(createdUserprofile,HttpStatus.OK);
        }
        catch(UserProfileNotExistsException e) {
            entity= new ResponseEntity<String>("The userprofile DOES NOT exists .. "+e.getMessage(),HttpStatus.NOT_FOUND);
        }

        return entity;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfile user,@PathVariable ("userId") int userId){
        ResponseEntity<?> entity =null;
        try {
            UserProfile updatedUserProfile = userProfileService.updateUserProfile(user,userId);
            entity = new ResponseEntity<UserProfile>(updatedUserProfile,HttpStatus.OK);
        }catch (UserProfileNotExistsException e) {
            entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return entity;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserProfileById(@PathVariable ("userId") int userId){
        ResponseEntity<?> entity = null;
        try {
            userProfileService.deleteUserProfile(userId);
            entity= new ResponseEntity<String>("The userprofile has been deleted successfully",HttpStatus.OK);
        }
        catch(UserProfileNotExistsException e) {
            entity= new ResponseEntity<String>("The userprofile DOES NOT exists .. "+e.getMessage(),HttpStatus.NOT_FOUND);
        }

        return entity;
    }


//    @PostMapping("/publish")
//    public String publishMessage(@RequestBody UserProfile userProfile){
//        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange,RabbitMqConfig.routingKey,userProfile);
//        return "Message published";
//    }

//    @RabbitListener(queues = RabbitMqConfig.queue)
//    public void listener(UserProfileServiceController userProfileServiceController){
//        System.out.println(userProfileServiceController);
//    }
}
