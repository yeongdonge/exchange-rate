package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.eumus.Quote;
import exchange.rate.eumus.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ApiHandler apiHandler;
    private final Source source;
    private final List<Quote> quotes;


    public ExchangeRateServiceImpl(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
        this.source = (Source) apiHandler.getInfo().get("supportedSource");
        this.quotes = (List<Quote>) apiHandler.getInfo().get("supportedQuote");
    }

    @Override
    public BigDecimal getExchangeRate(String quote) {
        ExchangeResultDc exchangeResultDc = apiHandler.getApi();
        try {
            if (quotes.contains(Quote.valueOf((quote)))) {
                String rateQuote = source + quote;
                return new BigDecimal(String.valueOf(exchangeResultDc.getQuotes().get(rateQuote)));
            }
        } catch (IllegalArgumentException e) {
            return BigDecimal.valueOf(-1);
        }
        return BigDecimal.valueOf(-1);
    }
}
