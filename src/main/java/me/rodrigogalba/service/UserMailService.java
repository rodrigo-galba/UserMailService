package me.rodrigogalba.service;

import me.rodrigogalba.messaging.UserMailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMailService {

    private static final Logger log = LoggerFactory.getLogger(UserMailService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        final UserMailMessage message = new UserMailMessage("Hello there!");
        log.info("Sending message...");
        rabbitTemplate.convertAndSend("", "mail.message.queue", message);
    }
}
