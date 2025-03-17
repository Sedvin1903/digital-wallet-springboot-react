package com.stackroute.notificationservice.config;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE = "user_exchange";

    public static final String NOTIFICATION_QUEUE = "notify_queue";
    public static final String NOTIFICATION_ROUTING_KEY = "notify_routing_key";



    @Bean
    public Queue queueNotification() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public TopicExchange exchangeNotificaton() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingWallet(Queue queueNotification, TopicExchange exchangeNotification) {
        return BindingBuilder.bind(queueNotification).to(exchangeNotification).with(NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
