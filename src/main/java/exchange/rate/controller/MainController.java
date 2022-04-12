package exchange.rate.controller;

import exchange.rate.dc.ExchangeDc;
import exchange.rate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class MainController {
    private final ExchangeRateService exchangeRateService;

    @GetMapping()
    public String index() {
        return "index";
    }

    @RequestMapping()
    public String calculateExchangeRate(@Valid @ModelAttribute ExchangeDc exchangeDc) {
        String country = exchangeDc.getCountry();
        double amount = exchangeDc.getAmount();
        System.out.println("country = " + country);
        System.out.println("amount = " + amount);

        return "index";
    }
}
