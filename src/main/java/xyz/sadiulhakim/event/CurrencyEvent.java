package xyz.sadiulhakim.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import xyz.sadiulhakim.domain.Rate;

import java.io.Serial;

@Getter
public class CurrencyEvent extends ApplicationEvent {

    @Serial
    private static final long serialVersionUID = 889202626288526113L;
    private final Rate rate;

    public CurrencyEvent(Object source, Rate rate) {
        super(source);
        this.rate = rate;
    }

}
