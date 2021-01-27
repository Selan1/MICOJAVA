package io.github.bank.builder;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

public class BankApiRequestBuilder {

    public String buildCurrenciesParam(List<String> currencies) {
        if (isEmpty(currencies))
            return "";

        if (currencies.size() == 1) {
            return currencies.get(0);
        }

        StringBuilder currenciesString = new StringBuilder(currencies.get(0));

        for (int i = 1; i < currencies.size(); i++) {
            currenciesString.append(",");
            currenciesString.append(currencies.get(i));
        }

        return currenciesString.toString();
    }
}
