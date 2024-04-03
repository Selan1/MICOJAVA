package io.github.bank.exchange.repository;

import io.github.bank.exchange.entity.RateHistoryDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RateHistoryDateRepository extends JpaRepository<RateHistoryDate, Long> {

    @Query("select rhd from RateHistoryDate rhd where rhd.actualDate > :dateFrom and rhd.actualDate < :dateTo")
    List<RateHistoryDate> findRateHistoryByDates(LocalDate dateFrom, LocalDate dateTo);
}
