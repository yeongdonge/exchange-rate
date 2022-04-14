package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.eumus.Quote;
import exchange.rate.eumus.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ApiHandler apiHandler;
    private Source source;

    public ExchangeRateServiceImpl(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
        this.source = Source.USD;
    }

    @Override
    public BigDecimal getExchangeRate(String quote) {
        ExchangeResultDc exchangeResultDc = apiHandler.getApi();
        String rateQuote = source + quote;
        BigDecimal result = new BigDecimal(String.valueOf(exchangeResultDc.getQuotes().get(rateQuote)));

        return result;
    }

//    @Override
//    public Double transfer(Double amount) {
//
//    }
}
