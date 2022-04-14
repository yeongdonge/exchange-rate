package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;

import java.math.BigDecimal;

public interface ExchangeRateService {

    BigDecimal getExchangeRate(String quote);

//    Double transfer(Double amount);
}
