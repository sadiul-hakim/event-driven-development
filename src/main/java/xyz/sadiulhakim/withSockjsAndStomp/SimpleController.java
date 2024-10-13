package xyz.sadiulhakim.withSockjsAndStomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleController {

    //This annotation maps incoming messages from WebSocket clients to specific controller methods.
    // It's similar to @RequestMapping in regular Spring MVC, but for WebSocket message handling.

    /**
     * When a WebSocket client sends a message to a specific destination, like /app/chat, this method will be invoked.
     * The @MessageMapping annotation specifies the path or destination that triggers this method.
     * The @MessageMapping("${xyz.sadiulhakim.ws.mapping:''}") allows dynamic configuration using the ${xyz.sadiulhakim.ws.mapping} property.
     * If this property is empty (''), the fallback value will be used.
     */
    @MessageMapping("${xyz.sadiulhakim.ws.mapping:''}")

    // This annotation specifies the destination where the result of the method should be sent.
    // After the controller processes the message, the response is sent to the destination mentioned in @SendTo.
    // In the example, after the handleMessage() method processes the incoming message, the resulting message is sent to /topic/chat-room.
    // Any WebSocket clients subscribed to this topic will receive the message.
    @SendTo("/topic/chat-room")
    public ChatMessage chatRoom(ChatMessage message) {

        // This is a websocket controller end point. We have to receive the request and publish the message manually.
        // In the case of Spring Event we can directly publish an event from app or in ActiveMQ (JMS) we can directly
        // publish a message to the topic. But in this case the message is coming from frontend. SO we need to catch it.
        // Otherwise, if we do not have a frontend we can send the message from spring as well using SimpMessagingTemplate.
        System.out.println("Web Socket Sockjs/STOMP :: received >> " + message);
        return message;
    }
}

/**
 * Client sends a message: The client sends a message to the destination prefixed with /app (for example, /app/chat).
 *
 * @MessageMapping handles the message: The server method annotated with @MessageMapping("/chat") handles the message.
 * @SendTo sends the response: The method returns a response that is sent to a topic, such as /topic/chat-room, which clients can subscribe to.
 * Message broker delivers the message: The broker (either simple or an external one) delivers the message to all clients subscribed to /topic/chat-room.
 */
