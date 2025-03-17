package com.stackroute.notificationservice.consumer;


import com.stackroute.notificationservice.config.RabbitMqConfig;
import com.stackroute.notificationservice.model.User;
import com.stackroute.notificationservice.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private NotificationRepository notificationRepository;
    @RabbitListener(queues = RabbitMqConfig.NOTIFICATION_QUEUE)
    public void consumeMessageFromQueue(User user) {
        System.out.println("Message recieved from queue : " + user);
        notificationRepository.save(user);
    }
}
