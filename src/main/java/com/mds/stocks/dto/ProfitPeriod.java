package com.mds.stocks.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProfitPeriod(
        LocalDate bestBuyDate,
        BigDecimal bestBuyClosePrice,
        LocalDate bestSellDate,
        BigDecimal bestSellClosePrice,
        BigDecimal profit
) {
}
