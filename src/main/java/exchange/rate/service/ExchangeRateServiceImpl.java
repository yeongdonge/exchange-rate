package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.eumus.Quote;
import exchange.rate.eumus.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ApiHandler apiHandler;
    private final Source source;

    public ExchangeRateServiceImpl(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
        this.source = Source.USD;
    }

    @Override
    public BigDecimal getExchangeRate(String quote) {
        ExchangeResultDc exchangeResultDc = apiHandler.getApi();
        String rateQuote = source + quote;

        return new BigDecimal(String.valueOf(exchangeResultDc.getQuotes().get(rateQuote)));
    }

    public Map<String, Object> getInfo() {
        return apiHandler.getInfo();
    }

//    @Override
//    public Double transfer(Double amount) {
//
//    }
}
