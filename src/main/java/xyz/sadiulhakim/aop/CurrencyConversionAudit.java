package xyz.sadiulhakim.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import xyz.sadiulhakim.event.CurrencyConversionEvent;
import xyz.sadiulhakim.exception.BadCodeRuntimeException;

@Aspect
@Component
@RequiredArgsConstructor
public class CurrencyConversionAudit {

    private final ApplicationEventPublisher publisher;

    @Pointcut("execution(* xyz.sadiulhakim.service.*Service.*(..))")
    public void exceptionPointcut() {
    }

    @AfterThrowing(pointcut = "exceptionPointcut()", throwing = "ex")
    public void badCodeException(JoinPoint jp, BadCodeRuntimeException ex) {

        if (ex.getConversion() != null) {
            publisher.publishEvent(new CurrencyConversionEvent(jp.getTarget(), ex.getMessage(), ex.getConversion()));
        }
    }

}
