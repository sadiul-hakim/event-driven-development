package xyz.sadiulhakim;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.sadiulhakim.config.JmsProperties;
import xyz.sadiulhakim.domain.Rate;
import xyz.sadiulhakim.rate.RateDTO;
import xyz.sadiulhakim.jms_with_activeMQ.SimpleSender;
import xyz.sadiulhakim.rate.RateSender;
import xyz.sadiulhakim.service.CurrencyService;

@RequiredArgsConstructor
@SpringBootApplication
public class RestApiEventsApplication implements CommandLineRunner {
    private final CurrencyService service;
    private final SimpleSender simplePublisher;
    private final JmsProperties jmsProperties;
    private final RateSender rateSender;

    public static void main(String[] args) {
        SpringApplication.run(RestApiEventsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Rate eur = new Rate("EUR", 0.88857F, new Date());
        Rate jpy = new Rate("JPY", 102.17F, new Date());
        Rate mxn = new Rate("MXN", 19.232F, new Date());
        Rate gbp = new Rate("GBP", 0.75705F, new Date());
        RateDTO eurDTO = service.saveRate(eur);
        RateDTO jpyDTO = service.saveRate(jpy);
        RateDTO mxnDTO = service.saveRate(mxn);
        RateDTO gbpDTO = service.saveRate(gbp);

        simplePublisher.sendMessage(jmsProperties.getQueue(), "Hi! This is JMS with ActiveMQ");
        rateSender.send(jmsProperties.getRateQueue(), eurDTO);
        rateSender.send(jmsProperties.getRateQueue(), jpyDTO);
        rateSender.send(jmsProperties.getRateQueue(), mxnDTO);
        rateSender.send(jmsProperties.getRateQueue(), gbpDTO);
    }
}
