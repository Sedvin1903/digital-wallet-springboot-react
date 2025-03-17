package com.stackroute.walletservice.consumer;

import java.util.HashSet;
import java.util.Set;


import com.stackroute.walletservice.config.RabbitMqConfigWallet;
import com.stackroute.walletservice.model.WalletModel;
import com.stackroute.walletservice.repository.WalletRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @Autowired
    private WalletRepository walletRepository;
    @RabbitListener(queues = RabbitMqConfigWallet.QUEUE)
    public void consumeMessageFromQueue(WalletModel wallet) {
        System.out.println("Message received from wallet queue : " + wallet);
        walletRepository.save(wallet);
    }
}