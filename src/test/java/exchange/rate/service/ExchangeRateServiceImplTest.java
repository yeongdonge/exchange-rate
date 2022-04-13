package exchange.rate.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.eumus.Quote;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ExchangeRateServiceImplTest {
    /**
     * API 호출 테스트
     * http://api.currencylayer.com/live?access_key=85c7c9db50eb0375d0cc55ab69e44908&source=USD&currencies=KRW,JPY,PHP&format=1
     */
    @Test
    @DisplayName("환율 API 호출 테스트")
    void API_호출() throws Exception {
        //given
        WebClient webClient = WebClient.create("http://api.currencylayer.com");

        //when
        String response = webClient.get()
                .uri("live?access_key={access_key}&source={source}&currencies={currencies}&format=1", Map.of(
                        "access_key", "85c7c9db50eb0375d0cc55ab69e44908",
                        "source", "USD",
                        "currencies", "KRW")
                ).retrieve()
                .bodyToMono(String.class)
                .block();

        //then
        JSONObject ob = new JSONObject(response);

        boolean success = Boolean.parseBoolean(ob.getString("success"));
        String source = ob.getString("source");
        String quote = ob.getJSONObject("quotes").getString("USDKRW");

        assertThat(success).isTrue();
        assertThat(source).isEqualTo("USD");
        assertThat(quote).isGreaterThan("1200");
    }

    /**
     * API 호출 테스트 2
     */
    @Test
    @DisplayName("API 호출 테스트 2")
    void test() throws Exception {
        WebClient webClient = WebClient.create("http://api.currencylayer.com");
        String url = "http://api.currencylayer.com/";
        String accessKey = "85c7c9db50eb0375d0cc55ab69e44908";

        //when
        String response = webClient.get()
                .uri(url + "live?access_key={access_key}",
                        Map.of("access_key", accessKey
                        ))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ExchangeResultDc exchangeResultDc = objectMapper.readValue(response, ExchangeResultDc.class);
        System.out.println("exchangeResultDc = " + exchangeResultDc);

    }
    //then

}
