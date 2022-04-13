package exchange.rate.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.eumus.Quote;
import exchange.rate.eumus.Source;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.simple.JSONObject;
import reactor.core.publisher.Mono;

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
    @JsonIgnoreProperties(ignoreUnknown = false)
    public ExchangeResultDc getApi() {
        ExchangeResultDc exchangeResultDc = null;

        String response = webClient.get()
                .uri(url + "live?access_key={access_key}",
                        Map.of("access_key", accessKey
                        ))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            exchangeResultDc = objectMapper.readValue(response, ExchangeResultDc.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return exchangeResultDc;
    }
}
