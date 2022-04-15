package exchange.rate.controller;

import exchange.rate.dc.ExchangeDc;
import exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class MainController {
    private final ExchangeRateService exchangeRateService;
    private final DecimalFormat df = new DecimalFormat("#,###.##");
    @GetMapping("/quote")
    public BigDecimal calculateExchangeRate(@RequestParam String quote) {
//        String exchangeRate = df.format(exchangeRateService.getExchangeRate(quote));
//        log.info(exchangeRate.toString());
//        return new BigDecimal(exchangeRate);
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(quote);
        log.info(exchangeRate.toString());
        return exchangeRate;
    }

    @PostMapping("/quote")
    public BigDecimal transferToQuote(@ModelAttribute ExchangeDc exchangeDc) {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(exchangeDc.getQuote());

        return new BigDecimal(String.valueOf(exchangeRate.multiply(exchangeDc.getAmount())));
    }

//    @GetMapping("{quote}")
}
