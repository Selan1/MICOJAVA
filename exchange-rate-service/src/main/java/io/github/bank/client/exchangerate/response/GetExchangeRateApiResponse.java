package io.github.bank.client.exchangerate.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Singular;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
public class GetExchangeRateApiResponse {

    @NotEmpty
    private String base;

    @NotEmpty
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @Singular
    private Map<String, Double> rates;
}
