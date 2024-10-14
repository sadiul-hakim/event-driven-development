# Spring Events (Observer Pattern, One Subject multiple Observer)

***We can create custom event and publish from anywhere of our spring application. Then we can listen to that
event from listener. And take actions.***

1. Implement ApplicationEvent to create an Event
2. Implement ApplicationListener or Use @EventListener,@TransactionalEventListener to capture an Event
3. Use ApplicationEventPublisher to publish an Event

# JMS with Active MQ (Sending message in queue)

***Using JMS we can send a message to queue and the receiver and receive that message and send a feedback (pTOp).
Or we can publish a message under a topic then subscribers can subscribe to the topic.***

1. Pull ActiveMQ in docker and run it.
2. Add activeMQ starter in spring boot app.
3. Set broker url,username,password and queues name in properties file
4. Configure `MessageListenerContainer` and MessageConverter
5. Use JmsTemplate to send Message
6. Use MessageListener to consume message
7. Alternatively use @JmsListener(destination = "${}") to consume message.
   `In this case no need to configure MessageListenerContainer`.
8. Use @SendTo to send feedback to the sender

## For JMS Topic (Sending message in topic)

1. Enable it in properties file
2. Put topic name

***Other than these changes, everything is same. The only change in behaviour is the sender would send message in topic
instead of queue. And the listener
would subscribe to that topic and listen from that.***

# WebSocket

***In Web Socket, when i someone connect to the socket and send a message we can receive that message and we can broadcast it to eventone, we can send to a specific user etc.***

## Low Level

1. Add dependency
2. Create a message Handler
3. Register that handler to an endpoint
4. Write your frontend code

## With SockJs and STOMP

1. Put properties in file
2. Configure endpoint,prefix,destinationPrefix

Web Socket uses In Memory broker by default. We can use ActiveMQ as `message broker`.

### Broker

***A message broker is a software intermediary that facilitates communication between different systems or components by
transmitting messages between them. It acts as a middleman that ensures messages are routed correctly, delivered
efficiently, and handled reliably, even in complex distributed systems. In a messaging system using a message broker, you can have multiple topics and brokers.***
