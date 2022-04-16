package exchange.rate.dc;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Data
@AllArgsConstructor
public class ExchangeDc {

    @NotBlank
    private String quote;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10000")
    private BigDecimal amount;
}
