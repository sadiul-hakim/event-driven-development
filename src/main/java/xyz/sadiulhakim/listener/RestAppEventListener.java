package xyz.sadiulhakim.listener;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import xyz.sadiulhakim.annotation.Log;

import java.util.Arrays;

@Component
public class RestAppEventListener { // No need to implement the ApplicationListener interface

    @EventListener({SpringApplicationEvent.class})
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Log(printParamsValues = true)
    public void restAppHandler(SpringApplicationEvent springApp) {
        System.out.println("SpringApplicationEvent received");
        System.out.println("Spring Application Arguments : " + Arrays.toString(springApp.getArgs()));
    }
}
