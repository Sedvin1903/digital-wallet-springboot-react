package com.stackroute.userservice.consumer;

import com.stackroute.userservice.config.RabbitMqConfig;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserRepository userRepository;
    @RabbitListener(queues = RabbitMqConfig.PROFILE_QUEUE)
    public void consumeMessageFromQueue(User user) {
        System.out.println("Message recieved from queue : " + user);
        userRepository.save(user);
    }
}
