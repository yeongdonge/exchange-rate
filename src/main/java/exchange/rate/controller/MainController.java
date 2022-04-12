package exchange.rate.controller;

import exchange.rate.dc.ExchangeDc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("{country}&{amount}")
    public String calculate(@PathVariable String country, @PathVariable double amount) {
        ExchangeDc exchangeDto = new ExchangeDc();
        exchangeDto.setCountry(country);
        exchangeDto.setAmount(amount);

        String result = exchangeDto.toString();

        return result;
    }
}
