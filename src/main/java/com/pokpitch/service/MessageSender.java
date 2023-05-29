package com.pokpitch.service;

import com.pokpitch.constant.MessagingConstant;
import com.pokpitch.domain.TestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class MessageSender {
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    public MessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
        UUID uuid = UUID.randomUUID();
        TestMessage message = new TestMessage(
                uuid.toString(),
                String.format("%06d",new Random().nextInt(999999)),
                "completed",
                true
        );
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(MessagingConstant.EXCHANGE_NAME, MessagingConstant.ROUTING_KEY, message);
    }
}
