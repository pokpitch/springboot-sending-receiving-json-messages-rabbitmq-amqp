package com.pokpitch.configuration;

import com.pokpitch.constant.MessagingConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(MessagingConstant.EXCHANGE_NAME);
    }

    @Bean
    public Queue appAnonymousQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding declareBindingAnonymous() {
        return BindingBuilder.bind(appAnonymousQueue()).to(appExchange()).with(MessagingConstant.BINDING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
