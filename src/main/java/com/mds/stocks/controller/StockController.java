package com.mds.stocks.controller;
import com.mds.stocks.entity.Company;
import com.mds.stocks.entity.Stock;
import com.mds.stocks.service.StockService;
import com.mds.stocks.service.StockService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;
    private static Logger logger = LoggerFactory.getLogger(StockController.class);
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("{companyId}")
    public List<Stock> findStocksByCompanyId(@PathVariable Long companyId) {
        return stockService.findStocksByCompanyId(companyId);
    }
}
