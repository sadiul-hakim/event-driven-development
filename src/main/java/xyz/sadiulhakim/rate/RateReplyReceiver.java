package xyz.sadiulhakim.rate;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import xyz.sadiulhakim.domain.Rate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class RateReplyReceiver {

    /**
     * This is a receiver and sender. This receives the message and sends a feedback the sender (in a different queue).
     */
    @JmsListener(destination = "${xyz.sadiulhakim.jms.rate-queue}")
    @SendTo("${xyz.sadiulhakim.jms.rate-reply-queue}")
    public Message<String> processRate(RateDTO rate) {
        System.out.println("JMS (ActiveMQ) pTop :: received => " + rate);

        // Send a reply to the sender. Actually write the feedback in a different queue.
        // Sender -> rate-queue -> receiver
        // receiver -> reply-rate -> sender
        return MessageBuilder
                .withPayload("PROCCESSED")
                .setHeader("CODE", rate.code())
                .setHeader("RATE", rate.rate())
                .setHeader("ID", UUID.randomUUID().toString())
                .setHeader("DATE",
                        new SimpleDateFormat("yyyy-MM-dd")
                                .format(new Date()))
                .build();
    }
}
