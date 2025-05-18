package com.mds.stocks.dto;
import java.math.BigDecimal;
public record ProfitResultResponse(
        ProfitPeriod currentPeriod,
        ProfitPeriod previousPeriod,
        ProfitPeriod nextPeriod,
        BigDecimal totalProfit


) {

}