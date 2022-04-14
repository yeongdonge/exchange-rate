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

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class MainController {
    private final ExchangeRateService exchangeRateService;

    @GetMapping()
    public String index() {

        return "index";
    }

    @GetMapping("/quote")
    @ResponseBody
    public String calculateExchangeRate(@RequestParam String quote) {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(quote);
        log.info(exchangeRate.toString());
        return String.valueOf(exchangeRate);
    }

    @PostMapping("{quote}/{amount}")
    public String transferToQuote(@Valid @RequestBody ExchangeDc exchangeDc) {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(exchangeDc.getQuote());

        BigDecimal amount = new BigDecimal(String.valueOf(exchangeDc.getAmount()));

        BigDecimal result = exchangeRate.multiply(amount);

        System.out.println(exchangeRate);
        System.out.println(exchangeDc.getAmount());
        log.info(exchangeRate.toString());
        log.info(String.valueOf(result));

        return "ok";
    }

//    @GetMapping("{quote}")
}
