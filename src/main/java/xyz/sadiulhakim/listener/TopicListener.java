package xyz.sadiulhakim.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import xyz.sadiulhakim.rate.RateDTO;

@Component
public class TopicListener {

    @JmsListener(destination = "${xyz.sadiulhakim.jms.topic}")
    public void listen(RateDTO rate) {
        System.out.println("JMS (ActiveMQ) Topic :: listened to " + rate.code());
    }
}
