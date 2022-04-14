package exchange.rate.dc;

import exchange.rate.eumus.Quote;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Data
public class ExchangeResultDc {
    private boolean success;
    private int timestamp;
    private String source;
//    private Quote quote;
    private Map<String, BigDecimal> quotes;
}
