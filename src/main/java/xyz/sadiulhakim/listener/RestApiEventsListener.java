package xyz.sadiulhakim.listener;

import xyz.sadiulhakim.annotation.Log;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class RestApiEventsListener implements ApplicationListener<ApplicationEvent> {

    private static final String LATEST = "/currency/latest";
    private static final AtomicLong COUNT = new AtomicLong(0);

    @Log(printParamsValues = true)
    public void onApplicationEvent(ApplicationEvent event) {
//        System.out.println("RestApiEventsListener :: " + event.getClass().getName());

        if (event instanceof ServletRequestHandledEvent requestHandledEvent &&
                requestHandledEvent.getRequestUrl().equals(LATEST)) {

            COUNT.getAndIncrement();
            System.out.println("RestApiEventsListener :: " + COUNT.get());
        }

    }
}