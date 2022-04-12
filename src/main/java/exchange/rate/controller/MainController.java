package exchange.rate.controller;

import exchange.rate.dc.ExchangeDc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("exchange/{country}/{amount}")
    public String calculateExchangeRate(@PathVariable String country, @PathVariable double amount, Model model) {
        ExchangeDc exchangeDc = new ExchangeDc(country, amount);
        model.addAttribute("exchangeDc", exchangeDc);

        return "ok";
    }
}
