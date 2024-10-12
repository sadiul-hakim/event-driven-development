package xyz.sadiulhakim.config;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.MessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableConfigurationProperties(JmsProperties.class)
public class JmsConfig {

    @Bean
    public MessageConverter jacksonMessageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_class_");
        return messageConverter;
    }

    /*
        Only needed for MessageListener interface
     */
    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(
            ConnectionFactory connectionFactory,
            MessageListener messageListener,
            @Value("${xyz.sadiulhakim.jms.queue}") final String destinationName
    ) {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setMessageListener(messageListener);
        messageListenerContainer.setDestinationName(destinationName);
        return messageListenerContainer;
    }
}
