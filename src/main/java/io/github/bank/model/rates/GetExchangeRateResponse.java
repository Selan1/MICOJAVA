package io.github.bank.model.rates;

import lombok.*;

import java.util.List;

@Data
@Builder
public class GetExchangeRateResponse {

    @Singular
    private List<CurrencyExchangeRates> currencyExchangeRates;
}
