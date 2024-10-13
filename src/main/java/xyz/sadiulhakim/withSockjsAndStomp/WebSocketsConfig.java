package xyz.sadiulhakim.withSockjsAndStomp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketsConfig implements WebSocketMessageBrokerConfigurer {

    // The endpoint is the URL or path that clients use to connect to the WebSocket server.
    // It's where clients initiate the WebSocket connection.
    @Value("${xyz.sadiulhakim.ws.endpoint:''}")
    private String endpoint;

    // A topic is a broadcast channel for WebSocket clients. Clients can subscribe to a topic,
    // and all messages sent to that topic are broadcasted to all subscribers.
    // In your WebSocket configuration, topics typically start with /topic.
    // Clients subscribed to /topic/something will receive all messages sent to that topic.
    @Value("${xyz.sadiulhakim.ws.topic:''}")
    private String topic;

    // The destination prefix is used to route messages to application-specific message-handling methods (like methods annotated with @MessageMapping).
    @Value("${xyz.sadiulhakim.ws.app-destination-prefix:''}")
    private String destinationPrefix;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(endpoint).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // The message broker is responsible for routing messages between the server and clients. It can be simple (in-memory) or full-featured (e.g., RabbitMQ, ActiveMQ).
        // It handles sending messages to the correct destination (like a topic or a queue).
        registry.enableSimpleBroker(topic);

        // When client would send a message. They would need to send the message at /{destination-prefix}/{mapping}
        // But for @MessageMapping there is not need to put /{destination-prefix} it is automatic
        registry.setApplicationDestinationPrefixes(destinationPrefix);
    }
}
