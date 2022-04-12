package exchange.rate.dc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Data
@AllArgsConstructor
public class ExchangeDc {

    @NotNull
    private String country;

    @Min(value = 0)
    @Max(value = 10000)
    private double amount;
}
