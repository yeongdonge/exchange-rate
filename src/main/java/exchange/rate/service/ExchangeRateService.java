package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ExchangeRateService {

    BigDecimal getExchangeRate(String quote);

}
