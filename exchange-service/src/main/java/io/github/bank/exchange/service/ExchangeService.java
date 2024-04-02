package io.github.bank.exchange.service;

import io.github.bank.common.dto.ConversionResult;
import io.github.bank.common.dto.ExchangeRates;
import io.github.bank.exchange.client.ExchangeRateAdapterClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRateAdapterClient adapterClient;

    //todo db
    public ExchangeRates getActualRates(String baseCurrency) {
        //try to search in DB, if no data, search online, save and return
        return adapterClient.getActualRates(baseCurrency);
    }

    public List<ExchangeRates> getHistoricalRates(String baseCurrency, LocalDate dateFrom, LocalDate dateTo) {
        //try to search in DB, if no data, search online, save and return
        return adapterClient.getHistoricalRates(baseCurrency, dateFrom, dateTo);
    }

    public ConversionResult convertRates(String fromCurrency, String toCurrency, Double amount) {

        //try to convert by actual info in DB, if no data, convert online, save and return
        return adapterClient.convert(fromCurrency, toCurrency, amount);
    }
}
