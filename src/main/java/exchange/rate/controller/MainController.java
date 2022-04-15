package exchange.rate.controller;

import exchange.rate.dc.ExchangeDc;
import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.function.BiConsumer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class MainController {
    private final ExchangeRateService exchangeRateService;


    @GetMapping("/quote")
    public BigDecimal calculateExchangeRate(@RequestParam String quote) {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(quote);
        log.info(exchangeRate.toString());
        return exchangeRate;
    }

    @PostMapping("{quote}/{amount}")
    public BigDecimal transferToQuote(@Valid @RequestBody ExchangeDc exchangeDc) {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(exchangeDc.getQuote());

        return new BigDecimal(String.valueOf(exchangeRate.multiply(exchangeDc.getAmount())));
    }

//    @GetMapping("{quote}")
}
