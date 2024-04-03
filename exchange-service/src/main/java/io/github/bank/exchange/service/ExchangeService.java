package io.github.bank.exchange.service;

import io.github.bank.common.dto.ConversionResult;
import io.github.bank.common.dto.ExchangeRates;
import io.github.bank.exchange.client.ExchangeRateAdapterClient;
import io.github.bank.exchange.mapping.ExchangeRatesMapper;
import io.github.bank.exchange.repository.RateHistoryDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.bank.exchange.mapping.ExchangeRatesMapper.fromExchangeRateHistory;
import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRateAdapterClient adapterClient;
    private final RateHistoryDateRepository repository;


    public ExchangeRates getActualRates(String baseCurrency) {
        return adapterClient.getActualRates(baseCurrency);
    }

    public List<ExchangeRates> getHistoricalRates(String baseCurrency, LocalDate dateFrom, LocalDate dateTo) {
        var historyRates = repository.findRateHistoryByDates(dateFrom, dateTo);

        if (!historyRates.isEmpty()) {
            return historyRates.stream()
                    .map(ExchangeRatesMapper::fromExchangeRateHistory)
                    .collect(toList());
        }

        var exchangeRates = adapterClient.getHistoricalRates(baseCurrency, dateFrom, dateTo);

        //todo mapping to usd
        if ("USD".equals(baseCurrency)) {
            historyRates = exchangeRates.stream()
                    .map(ExchangeRatesMapper::toExchangeRateHistory)
                    .collect(toList());

            repository.saveAll(historyRates);
        }

        return exchangeRates;
    }

    public ConversionResult convertRates(String fromCurrency, String toCurrency, Double amount) {
        return adapterClient.convert(fromCurrency, toCurrency, amount);
    }
}
