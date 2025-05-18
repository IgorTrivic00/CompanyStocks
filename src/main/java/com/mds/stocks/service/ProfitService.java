package com.mds.stocks.service;

import com.mds.stocks.dto.ProfitResultResponse;
import com.mds.stocks.dto.ProfitPeriod;
import com.mds.stocks.entity.Stock;
import com.mds.stocks.repository.StockRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

import java.util.List;
import java.time.Period;
@Service
public class ProfitService {

    private final StockRepository stockRepository;


    public ProfitService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;

    }

    public ProfitResultResponse calculateProfit(String symbol, LocalDate fromDate, LocalDate toDate) {
        int periodLength = (int) Duration.between(fromDate.atStartOfDay(), toDate.atStartOfDay()).toDays() + 1;
        ProfitPeriod currentPeriod = calculateProfitPeriod(symbol, fromDate, toDate);
        ProfitPeriod previousPeriod = calculateProfitPeriod(symbol, fromDate.minusDays(periodLength), fromDate.minusDays(1));
        ProfitPeriod nextPeriod = calculateProfitPeriod(symbol, toDate.plusDays(1), toDate.plusDays(periodLength));

        List<Stock> currentPeriodData = stockRepository.findStocksTimeIntervalByCompanySymbol(symbol, fromDate, toDate);
        BigDecimal totalProfit = calculateTotalProfit(currentPeriodData);

        return new ProfitResultResponse(currentPeriod, previousPeriod, nextPeriod, totalProfit);
    }

    private ProfitPeriod calculateProfitPeriod(String symbol, LocalDate start, LocalDate end) {
        List<Stock> stocks = stockRepository.findStocksTimeIntervalByCompanySymbol(symbol, start, end);
        if (stocks.isEmpty() || stocks.size() < 2) return null;

        Stock bestBuy = stocks.get(0);
        Stock bestSell = stocks.get(0);
        Stock minStock = stocks.get(0);
        BigDecimal maxProfit = BigDecimal.ZERO;

        for (int i = 1; i < stocks.size(); i++) {
            Stock current = stocks.get(i);
            BigDecimal profit = current.getClosePrice().subtract(minStock.getClosePrice());

            if (profit.compareTo(maxProfit) > 0) {
                maxProfit = profit;
                bestBuy = minStock;
                bestSell = current;
            }

            if (current.getClosePrice().compareTo(minStock.getClosePrice()) < 0) {
                minStock = current;
            }
        }

        return new ProfitPeriod(
                bestBuy.getDate(),
                bestBuy.getClosePrice(),
                bestSell.getDate(),
                bestSell.getClosePrice(),
                maxProfit
        );
    }

    private BigDecimal calculateTotalProfit(List<Stock> data) {
        BigDecimal totalProfit = BigDecimal.ZERO;
        for (int i = 1; i < data.size(); i++) {
            BigDecimal diffrence = data.get(i).getClosePrice().subtract(data.get(i - 1).getClosePrice());
            if (diffrence.compareTo(BigDecimal.ZERO) > 0) {
                totalProfit = totalProfit.add(diffrence);
            }
        }
        return totalProfit;
    }



}