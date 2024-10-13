package xyz.sadiulhakim;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import xyz.sadiulhakim.config.JmsProperties;
import xyz.sadiulhakim.domain.Rate;
import xyz.sadiulhakim.low.level.web_socket.LlWebSocketHandler;
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
    private final LlWebSocketHandler handler;

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

        // Send to topic
        rateSender.send(jmsProperties.getTopic(), eurDTO);
        rateSender.send(jmsProperties.getTopic(), jpyDTO);
        rateSender.send(jmsProperties.getTopic(), mxnDTO);
        rateSender.send(jmsProperties.getTopic(), gbpDTO);

//        sendSocketMessage();
    }

    private void sendSocketMessage() {
        // So far we are sending message from html using javascript. But we can send socket message from
        // Java as well. For that we need a client.
        try {
            System.out.println(">> Sending web socket message from spring boot");
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(Map.of("user", "Spring-Boot", "message", "From Java Client"));

            var client = new StandardWebSocketClient();
            ListenableFuture<WebSocketSession> future = client.doHandshake(handler, new WebSocketHttpHeaders(), new URI("ws://localhost:9091/llws"));
            WebSocketSession session = future.get();
            WebSocketMessage<String> message = new TextMessage(msg);
            session.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
