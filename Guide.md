# Spring Events (Observer Pattern, One Subject multiple Observer)

1. Implement ApplicationEvent to create an Event
2. Implement ApplicationListener or Use @EventListener,@TransactionalEventListener to capture an Event
3. Use ApplicationEventPublisher to publish an Event

# JMS with Active MQ (Sending message in queue)
1. Pull ActiveMQ in docker and run it.
2. Add activeMQ starter in spring boot app.
3. Set broker url,username,password and queues name in properties file
4. Configure `MessageListenerContainer` and MessageConverter
5. Use JmsTemplate to send Message
6. Use MessageListener to consume message
7. Alternatively use @JmsListener(destination = "${}") to consume message. `In this case no need to configure MessageListenerContainer`.
8. Use @SendTo to send feedback to the sender

## For JMS Topic (Sending message in topic)
1. Enable it in properties file
2. Put topic name

***Other than these changes, everything is same. The only change in behaviour is the sender would send message in topic instead of queue. And the listener
would subscribe to that topic and listen from that.***