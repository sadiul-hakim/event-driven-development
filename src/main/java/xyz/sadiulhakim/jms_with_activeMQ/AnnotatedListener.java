package xyz.sadiulhakim.jms_with_activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedListener {

    @JmsListener(destination = "${xyz.sadiulhakim.jms.queue}")
    public void listen(String content) {
        System.out.println("JMS :: (" + content + ") received.");
    }
}
