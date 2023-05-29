package com.pokpitch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "#{appAnonymousQueue.name}")
    public void receiveMessage(final Message message) {
        log.info("Received message as a binding key AMQP 'Message' wrapper: {}", message.toString());
    }

}
