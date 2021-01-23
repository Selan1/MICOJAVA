package io.github.bank.controller;

import io.github.bank.model.rates.GetExchangeRateRequest;
import io.github.bank.model.rates.GetExchangeRateResponse;
import io.github.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankServiceHttpSyncController {

    private final BankService bankService;

    @Autowired
    public BankServiceHttpSyncController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/getExchangeRates")
    private GetExchangeRateResponse getExchangeRates(@Valid @RequestBody GetExchangeRateRequest request) {
        return bankService.getExchangeRate(request);
    }
}
