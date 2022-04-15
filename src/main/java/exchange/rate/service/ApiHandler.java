package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public interface ApiHandler {
    ExchangeResultDc getApi();

    Map<String, Object> getInfo();


}
