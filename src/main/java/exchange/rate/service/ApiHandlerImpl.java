package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.eumus.Quote;
import exchange.rate.eumus.Source;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ApiHandlerImpl implements ApiHandler {
    private final WebClient webClient;
    @Value("${custom.accessKey}")
    private String accessKey;
    private String url;
    private List<Quote> supportedQuote;
    private List<Source> supportedSource;

    public ApiHandlerImpl(WebClient.Builder webClientBuilder) {
        this.url = "http://api.currencylayer.com/";
        this.webClient = webClientBuilder.baseUrl(url).build();
        supportedQuote = new ArrayList<>(List.of(Quote.KRW, Quote.PHP, Quote.JPY));
        supportedSource = new ArrayList<>(List.of(Source.USD));
    }

    @Override
    public ExchangeResultDc getApi() {
        return webClient.get()
                .uri("live?access_key={access_key}",
                        Map.of("access_key", accessKey
                        ))
                .retrieve()
                .bodyToMono(ExchangeResultDc.class)
                .block();
    }
}
