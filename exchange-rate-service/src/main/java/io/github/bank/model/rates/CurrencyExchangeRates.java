package io.github.bank.model.rates;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
public class CurrencyExchangeRates {

    @NotEmpty
    private String baseCurrency;

    @NotEmpty
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastUpdate;

    @NotNull
    @Singular
    private Map<String, Double> currencyExchangeRates;
}
