package com.stackroute.userservice.ServiceImplTest;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @BeforeEach
    @AfterEach
    void setup(){
        userRepository.deleteAll();
    }
    @Test
    void testRegisterUser(){
        User user = createUser();
        userRepository.save(user);
        userService.registerUser(user);
        Assertions.assertEquals(1,userRepository.findAll().size());
    }

    private User createUser(){
        User user = new User();
        user.setFirstName("surya");
        user.setLastName("b");
        user.setEmailId("surya@gmail.com");
        user.setPassword("User1@ps");
        user.setMobileNumber("9059229781");
        user.setWalletId(214325363);
        return user;
    }
}
