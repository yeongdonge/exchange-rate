package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.eumus.Quote;
import exchange.rate.eumus.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ApiHandler apiHandler;
    private Source source;

    public ExchangeRateServiceImpl(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
        this.source = Source.USD;
    }

    @Override
    public Double getExchangeRate(String quote) {
        ExchangeResultDc exchangeResultDc = apiHandler.getApi();
        Quote quoteEnum = Quote.valueOf(quote);

        return exchangeResultDc.getQuotes().get(quoteEnum);
    }
}
