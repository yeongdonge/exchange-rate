package exchange.rate.dc;

import exchange.rate.eumus.Quote;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ExchangeResultDc {
    private boolean success;
    private int timestamp;
    private String source;
    private Quote quote;
    private double value;
}
