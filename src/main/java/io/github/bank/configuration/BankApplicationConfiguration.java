package io.github.bank.configuration;

import io.github.bank.builder.BankApiRequestBuilder;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


@Data
@SpringBootConfiguration
@ConfigurationProperties("bank-service")
public class BankApplicationConfiguration {
    private String noSuchCurrencyErrorDescription;

    @Bean
    public BankApiRequestBuilder bankApiRequestBuilder() {
        return new BankApiRequestBuilder();
    }
}

