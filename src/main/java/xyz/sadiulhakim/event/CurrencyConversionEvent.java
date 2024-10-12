package xyz.sadiulhakim.event;

import org.springframework.context.ApplicationEvent;

import xyz.sadiulhakim.domain.CurrencyConversion;

import java.io.Serial;

public class CurrencyConversionEvent extends ApplicationEvent {

	@Serial
	private static final long serialVersionUID = -4481493963350551884L;
	private final CurrencyConversion conversion;
	private String message;
	
	public CurrencyConversionEvent(Object source, CurrencyConversion conversion) {
		super(source);
		this.conversion = conversion;
	}
	
	public CurrencyConversionEvent(Object source, String message, CurrencyConversion conversion) {
		super(source);
		this.message = message;
		this.conversion = conversion;
	}

	public CurrencyConversion getConversion(){
		return conversion;
	}
	
	public String getMessage(){
		return message;
	}
}
