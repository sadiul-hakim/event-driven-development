# Spring Events (Observer Pattern, One Subject multiple Observer)

1. Implement ApplicationEvent to create an Event
2. Implement ApplicationListener or Use @EventListener,@TransactionalEventListener to capture an Event
3. User ApplicationEventPublisher to publish an Event

# JMS with Active MQ
1. Pull ActiveMQ in docker and run it.
2. Add activeMQ starter in spring boot app.
3. Set properties in properties file
4. Configure `MessageListenerContainer` and MessageConverter
5. Use JmsTemplate to send Message
6. Use MessageListener to consume message
7. Alternatively use @JmsListener(destination = "${}") to consume message. `In this case no need to configure MessageListenerContainer`.