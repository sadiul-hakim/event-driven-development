package xyz.sadiulhakim.rate;


import java.util.Date;

public record RateDTO(
        String code,
        Float rate,
        Date data
) {
}
