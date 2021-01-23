package io.github.bank.service;

import io.github.bank.builder.BankApiRequestBuilder;
import io.github.bank.client.exchangerate.ExchangeRateApiClient;
import io.github.bank.client.exchangerate.response.GetExchangeRateApiResponse;
import io.github.bank.model.rates.CurrencyExchangeRates;
import io.github.bank.model.rates.GetExchangeRateRequest;
import io.github.bank.model.rates.GetExchangeRateResponse;
import io.github.bank.model.rates.GetExchangeRateResponse.GetExchangeRateResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;


@Service
public class BankService {

    private final ExchangeRateApiClient exchangeRateApiClient;

    @Autowired
    public BankService(ExchangeRateApiClient exchangeRateApiClient) {
        this.exchangeRateApiClient = exchangeRateApiClient;
    }

    public GetExchangeRateResponse getExchangeRate(GetExchangeRateRequest request) {
        if (isNull(request) || isNull(request.getCurrencies()) || isNull(request.getCurrencies().getCurrencyNames()))
            return GetExchangeRateResponse.builder().build();

        List<String> currencyNames = request.getCurrencies().getCurrencyNames();
        GetExchangeRateResponseBuilder builder = GetExchangeRateResponse.builder();

        for (String currencyName : currencyNames) {
            GetExchangeRateApiResponse exchangeRateApiResponse = exchangeRateApiClient.getExchangeRateInfo(currencyName);
            builder.currencyExchangeRate(CurrencyExchangeRates.builder()
                    .baseCurrency(exchangeRateApiResponse.getBase())
                    .lastUpdate(exchangeRateApiResponse.getDate())
                    .currencyExchangeRates(exchangeRateApiResponse.getRates())
                    .build());
        }

        return builder.build();
    }
}
