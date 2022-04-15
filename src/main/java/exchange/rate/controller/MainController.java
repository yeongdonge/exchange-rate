package exchange.rate.controller;

import exchange.rate.dc.ExchangeDc;
import exchange.rate.eumus.Quote;
import exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class MainController {
    private final ExchangeRateService exchangeRateService;
    private final DecimalFormat df = new DecimalFormat("#,##0.00");


//    @GetMapping("")
//    public String index(Model model) {
//        Map<String, Object> info = exchangeRateService.getInfo();
//        List<Quote> quotes = new ArrayList<>(info.get("supportedQuote"));
//
//        return ""
//    }

    @GetMapping("/quote")
    public String calculateExchangeRate(@RequestParam String quote) {
        df.setRoundingMode(RoundingMode.DOWN);
        String exchangeRate = df.format(exchangeRateService.getExchangeRate(quote));
        log.info(exchangeRate);
        return exchangeRate;
    }

    @PostMapping("/quote")
    public String transferToQuote(@ModelAttribute ExchangeDc exchangeDc) {
        df.setRoundingMode(RoundingMode.DOWN);
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(exchangeDc.getQuote()).setScale(2, RoundingMode.FLOOR);
        BigDecimal amount = exchangeDc.getAmount();
        BigDecimal transferAmount = exchangeRate.multiply(amount);
        log.info(exchangeRate.toString());
        log.info(amount.toString());
        return df.format(transferAmount);
    }

//    @GetMapping("{quote}")
}
