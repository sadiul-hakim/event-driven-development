package xyz.sadiulhakim;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import xyz.sadiulhakim.config.JmsProperties;
import xyz.sadiulhakim.domain.Rate;
import xyz.sadiulhakim.jms_with_activeMQ.SimpleSender;
import xyz.sadiulhakim.service.CurrencyService;

@RequiredArgsConstructor
@SpringBootApplication
public class RestApiEventsApplication implements CommandLineRunner {
    private final CurrencyService service;
    private final SimpleSender simplePublisher;
    private final JmsProperties jmsProperties;

    public static void main(String[] args) {
        SpringApplication.run(RestApiEventsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.saveRate(new Rate("EUR", 0.88857F, new Date()));
        service.saveRate(new Rate("JPY", 102.17F, new Date()));
        service.saveRate(new Rate("MXN", 19.232F, new Date()));
        service.saveRate(new Rate("GBP", 0.75705F, new Date()));

        simplePublisher.sendMessage(jmsProperties.getQueue(), "Hi! This is JMS with ActiveMQ");
    }
}
