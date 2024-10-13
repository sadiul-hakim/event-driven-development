package xyz.sadiulhakim.withSockjsAndStomp;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.sadiulhakim.domain.Rate;

@Controller
@RequiredArgsConstructor
public class AnotherController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(path = "/rate/new", method = RequestMethod.POST)
    public void newRates(@ModelAttribute Rate rate) {

        // Publish that message under topic : /topic
        // Subscribers of topic: /topic receive this message
        simpMessagingTemplate.convertAndSend("/topic/new-rate", rate);
    }
}
