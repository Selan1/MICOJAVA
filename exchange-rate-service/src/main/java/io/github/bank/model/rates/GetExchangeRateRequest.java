package io.github.bank.model.rates;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class GetExchangeRateRequest {

    @NotNull(message = "Currencies section is empty")
    @Valid
    private CurrencyNames currencies;
}
