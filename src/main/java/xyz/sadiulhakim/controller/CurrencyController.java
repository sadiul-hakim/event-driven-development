package xyz.sadiulhakim.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.sadiulhakim.domain.CurrencyConversion;
import xyz.sadiulhakim.domain.CurrencyExchange;
import xyz.sadiulhakim.domain.Rate;
import xyz.sadiulhakim.service.CurrencyConversionService;
import xyz.sadiulhakim.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
	

	private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);
	
	@Autowired 
	CurrencyConversionService conversionService;
	
	@Autowired 
	CurrencyService service;
	
	@RequestMapping("/latest")
	public ResponseEntity<CurrencyExchange> getLatest(@RequestParam(name="base",defaultValue=CurrencyExchange.BASE_CODE)String base){
		return new ResponseEntity<>(new CurrencyExchange(base, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), conversionService.calculateByCode(base, new Date())), HttpStatus.OK);
	}
	
	@RequestMapping("/{date}")
	public ResponseEntity<CurrencyExchange> getByDate(
			@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam(name="base",defaultValue=CurrencyExchange.BASE_CODE)String base
	){
		return new ResponseEntity<>(new CurrencyExchange(base, new SimpleDateFormat("yyyy-MM-dd").format(date), conversionService.calculateByCode(base, date)), HttpStatus.OK);
	}
	
	@RequestMapping("/{amount}/{base}/to/{code}")
	public ResponseEntity<CurrencyConversion> conversion(@PathVariable("amount")Float amount,@PathVariable("base")String base,@PathVariable("code")String code){
		CurrencyConversion conversionResult = conversionService.convertFromTo(base, code, amount);
		return new ResponseEntity<>(conversionResult, HttpStatus.OK);
	}
	
	@RequestMapping(path="/new",method = {RequestMethod.POST})
	public ResponseEntity<CurrencyExchange> addNewRates(@RequestBody CurrencyExchange currencyExchange) throws Exception {
		try{
			final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(currencyExchange.getDate());
			final Rate[] rates = currencyExchange.getRates();
			service.saveRates(rates,date);
		}catch(Exception ex){
			log.error(ex.getMessage());
			throw ex;
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
