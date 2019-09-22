package me.rodrigogalba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.rodrigogalba.messaging.MessagingConfig;
import me.rodrigogalba.messaging.UserMailMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserMailService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(UserMailMessage message) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend("", MessagingConfig.MAIL_MESSAGE_QUEUE, message);
    }
}
