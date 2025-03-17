package com.stackroute.userservice.config;


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
    public static final String QUEUE = "user_queue";
    public static final String ROUTING_KEY = "user_routing_key";

    public static final String WALLET_EXCHANGE = "wallet_exchange";
    public static final String WALLET_QUEUE = "wallet_queue";
    public static final String WALLET_ROUTING_KEY = "wallet_routing_key";

    public static final String PROFILE_QUEUE = "profile_queue";
    public static final String PROFILE_ROUTING_KEY = "profile_routing_key";

    public static final String NOTIFICATION_QUEUE = "notify_queue";
    public static final String NOTIFICATION_ROUTING_KEY = "notify_routing_key";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }


    @Bean
    public Queue queueWallet() {
        return new Queue(WALLET_QUEUE);
    }

    @Bean
    public TopicExchange exchangeWallet() {
        return new TopicExchange(WALLET_EXCHANGE);
    }

    @Bean
    public Binding bindingWallet(Queue queueWallet, TopicExchange exchangeWallet) {
        return BindingBuilder.bind(queueWallet).to(exchangeWallet).with(WALLET_ROUTING_KEY);
    }

    @Bean
    public Queue profileQueue() {
        return new Queue(PROFILE_QUEUE);
    }

    @Bean
    public Binding profileBinding(Queue profileQueue, TopicExchange exchange) {
        return BindingBuilder.bind(profileQueue).to(exchange).with(PROFILE_ROUTING_KEY);
    }

    @Bean
    public Queue queueNotification() {
        return new Queue(NOTIFICATION_QUEUE);
    }

//    @Bean
//    public TopicExchange exchangeNotificaton() {
//        return new TopicExchange(EXCHANGE);
//    }

    @Bean
    public Binding bindingNotification(Queue queueNotification, TopicExchange exchange) {
        return BindingBuilder.bind(queueNotification).to(exchange).with(NOTIFICATION_ROUTING_KEY);
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
