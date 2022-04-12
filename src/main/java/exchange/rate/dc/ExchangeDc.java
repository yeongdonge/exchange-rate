package exchange.rate.dc;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Data
public class ExchangeDc {

    @NotNull
    private String country;

    @Min(value = 0)
    @Max(value = 10000)
    private double amount;
}
