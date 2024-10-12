package xyz.sadiulhakim.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import xyz.sadiulhakim.annotation.Log;
import xyz.sadiulhakim.event.CurrencyEvent;

@Component
public class RateEventListener {

    //@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @TransactionalEventListener
    @Log(printParamsValues = true, callMethodWithNoParamsToString = "getRate")
    public void processEvent(CurrencyEvent event) {
        System.out.println("CurrencyEvent is published with code :: "+event.getRate().getCode());
    }
}
