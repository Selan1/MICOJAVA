package io.github.bank.model.rates;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class CurrencyNames {

    @NotEmpty(message="Currencies list is empty")
    @Singular
    private List<String> currencyNames;
}
