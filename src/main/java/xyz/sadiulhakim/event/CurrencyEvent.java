package xyz.sadiulhakim.event;

import org.springframework.context.ApplicationEvent;

import xyz.sadiulhakim.domain.Rate;

public class CurrencyEvent extends ApplicationEvent {

	private static final long serialVersionUID = 889202626288526113L;
	private Rate rate;
	
	public CurrencyEvent(Object source,Rate rate) {
		super(source);
		this.rate = rate;
	}
	
	public Rate getRate(){
		return this.rate;
	}
}
