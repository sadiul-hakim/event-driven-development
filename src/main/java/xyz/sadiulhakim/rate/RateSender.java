package xyz.sadiulhakim.rate;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateSender {
    private final JmsTemplate jmsTemplate;

    public void send(String destination, RateDTO rate) {
        jmsTemplate.convertAndSend(destination, rate);
    }

    @JmsListener(destination = "${xyz.sadiulhakim.jms.rate-reply-queue}")
    public void receiveFeedback(String body, @Header("CODE") String code) {
        System.out.println("JMS (ActiveMQ) :: received feedback for : " + code);
    }
}
