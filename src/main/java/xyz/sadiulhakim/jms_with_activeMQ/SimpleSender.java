package xyz.sadiulhakim.jms_with_activeMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

// This is a Producer
@Component
@RequiredArgsConstructor
public class SimpleSender {
    private final JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }
}
