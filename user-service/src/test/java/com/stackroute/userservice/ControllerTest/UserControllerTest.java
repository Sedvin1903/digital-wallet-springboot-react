package com.stackroute.userservice.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.controller.UserController;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.json.HTTP;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;
    @Autowired
    UserRepository userRepository;
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @BeforeEach
    @AfterEach
    void setup(){
        userRepository.deleteAll();
    }

    @Test
    void testRegisterUser() throws Exception {
        userRepository.save(createUser());
        String jsonBody = "{\n" +
                "    \"firstName\": \"jaya5\",\n" +
                "    \"lastName\": \"harris\",\n" +
                "    \"emailId\": \"user1@gmail.com\",\n" +
                "    \"mobileNumber\": \"4444444523\",\n" +
                "    \"password\": \"User1@gm\"\n" +
                "}";
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.post("digimon/v1/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBody))
                .andReturn().getResponse();

        Assertions.assertEquals(200,response.getStatus());
    }
    void testLoginUser(){}

    private User createUser(){
        User user = new User();
        user.setFirstName("surya");
        user.setLastName("b");
        user.setEmailId("surya@gmail.com");
        user.setPassword("User1@ps");
        user.setMobileNumber("9059229781");
        return user;
    }
}
