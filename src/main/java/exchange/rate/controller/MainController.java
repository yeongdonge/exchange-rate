package exchange.rate.controller;

import exchange.rate.dc.ExchangeDc;
import exchange.rate.eumus.Quote;
import exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
@Slf4j
public class MainController {
    private final ExchangeRateService exchangeRateService;
    private final DecimalFormat df = new DecimalFormat("#,##0.00");



    @GetMapping("/quote")
    public String calculateExchangeRate(@RequestParam String quote) {
        df.setRoundingMode(RoundingMode.DOWN);
        String exchangeRate = df.format(exchangeRateService.getExchangeRate(quote));
        log.info(exchangeRate);
        return exchangeRate;
    }

    @PostMapping("/quote")
    public String transferToQuote(@Valid @RequestBody ExchangeDc exchangeDc, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("valid 에러");
            return "error";
        }

        df.setRoundingMode(RoundingMode.DOWN);
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(exchangeDc.getQuote()).setScale(2, RoundingMode.FLOOR);
        BigDecimal transferAmount = exchangeRate.multiply(exchangeDc.getAmount());
        log.info(exchangeRate.toString());
        return df.format(transferAmount);
    }


//    @GetMapping("{quote}")
}
