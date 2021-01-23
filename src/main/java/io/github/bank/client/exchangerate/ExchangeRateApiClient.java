package io.github.bank.client.exchangerate;


import io.github.bank.client.exchangerate.response.GetExchangeRateApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//todo add different interceptors

@FeignClient(name = "exchangeApiClient", url = "${bank-service.exchangeApiClient.url}")
public interface ExchangeRateApiClient {

    @GetMapping(value = "/latest?base={currency}")
    GetExchangeRateApiResponse getExchangeRateInfo(@PathVariable(name = "currency") String currency);
}
