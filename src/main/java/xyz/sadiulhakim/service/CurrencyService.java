package xyz.sadiulhakim.service;

import java.util.Arrays;
import java.util.Date;

import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import xyz.sadiulhakim.domain.Rate;
import xyz.sadiulhakim.rate.RateDTO;
import xyz.sadiulhakim.event.CurrencyEvent;
import xyz.sadiulhakim.repository.RateRepository;

@Service
public class CurrencyService {
    private RateRepository repository;
    private ApplicationEventPublisher publisher;

    public CurrencyService(RateRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void saveRates(Rate[] rates, Date date) {
        Arrays.stream(rates).forEach(rate -> repository.save(new Rate(rate.getCode(), rate.getRate(), date)));
    }

    @Transactional
    public RateDTO saveRate(Rate rate) {
        Rate save = repository.save(new Rate(rate.getCode(), rate.getRate(), rate.getDate()));
        publisher.publishEvent(new CurrencyEvent(this, rate));
        return convert(save);
    }

    private RateDTO convert(Rate rate) {
        return new RateDTO(rate.getCode(), rate.getRate(), rate.getDate());
    }
}
