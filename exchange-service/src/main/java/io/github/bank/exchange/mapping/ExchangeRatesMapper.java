package io.github.bank.exchange.mapping;

import io.github.bank.common.dto.ExchangeRates;
import io.github.bank.exchange.entity.RateHistoryDate;
import io.github.bank.exchange.entity.RateHistoryValue;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@UtilityClass
public class ExchangeRatesMapper {

    public static ExchangeRates fromExchangeRateHistory(RateHistoryDate rateHistoryDate) {
        return ExchangeRates.builder()
                .rateDate(rateHistoryDate.getActualDate())
                .baseCurrency("USD")
                .exchangeRates(rateHistoryDate.getRateValues().stream()
                        .collect(toMap(RateHistoryValue::getCurrency, RateHistoryValue::getRate)))
                .build();
    }

    public static RateHistoryDate toExchangeRateHistory(ExchangeRates exchangeRates) {
        var historyRateDate = RateHistoryDate.builder()
                .actualDate(exchangeRates.getRateDate())
                .build();

        var rates = exchangeRates.getExchangeRates().entrySet().stream()
                .map(entry -> RateHistoryValue.builder()
                        .currency(entry.getKey())
                        .rate(entry.getValue())
                        .rateHistoryDate(historyRateDate)
                        .build())
                .collect(toList());

        historyRateDate.setRateValues(rates);

        return historyRateDate;
    }
}
