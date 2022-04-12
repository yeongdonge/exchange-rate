package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;

public interface ExchangeRateService {

    Double getExchangeRate(String quote);
}
