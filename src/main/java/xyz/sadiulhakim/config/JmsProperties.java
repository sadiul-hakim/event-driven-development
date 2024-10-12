package xyz.sadiulhakim.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "xyz.sadiulhakim.jms")
public class JmsProperties {
    private String queue;
    private String rateQueue;
    private String rateReplyQueue;
    private String topic;
}
