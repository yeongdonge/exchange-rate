package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final WebClient webClient;

    @Value("${custom.accessKey}")
    private String accessKey;

    private String url = "http://api.currencylayer.com/";
    private String source;
    private String currencies;

    public ExchangeRateServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    @Override
    public ExchangeResultDc getInformation() {

        return webClient.get()
                .uri("live?access_key={access_key}" +
                                "&source={source}" +
                                "&currencies={currencies}&format=1",
                        Map.of("access_key", accessKey,
                                "source", source,
                                "currencies", currencies))
                .retrieve()
                .bodyToMono(ExchangeResultDc.class)
                .block();
    }
}
