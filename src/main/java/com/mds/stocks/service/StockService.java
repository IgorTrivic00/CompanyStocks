package com.mds.stocks.service;


import com.mds.stocks.entity.Stock;
import com.mds.stocks.repository.StockRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public List<Stock> findStocksByCompanyId(Long companyId) {
        return stockRepository.findAllByCompanyId(companyId);
    }
}
