package com.mds.stocks.dto;
import java.time.LocalDate;
public record ProfitRequest(
          String symbol,
          LocalDate fromDate,
          LocalDate toDate) {
}
