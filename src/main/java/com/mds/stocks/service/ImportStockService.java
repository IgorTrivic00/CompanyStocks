package com.mds.stocks.service;
import com.mds.stocks.advice.exception.CompanyNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.mds.stocks.entity.Company;
import com.mds.stocks.entity.Stock;
import com.mds.stocks.repository.CompanyRepository;
import com.mds.stocks.repository.StockRepository;
import java.util.*;
@Service
public class ImportStockService {
    private final CompanyRepository companyRepository;
    private final StockRepository stockRepository;

    public ImportStockService(CompanyRepository companyRepository, StockRepository stockRepository) {
        this.companyRepository = companyRepository;
        this.stockRepository = stockRepository;
    }


    public void importCsv(MultipartFile file, String symbol) {
        Company company = companyRepository.findBySymbol(symbol)
                .orElseThrow(() -> new CompanyNotFoundException("The company with the symbol '" + symbol + "' does not exist in the database."));

        List<Stock> stocksList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new RuntimeException("The CSV file is empty.");
            }

            String[] headers = headerLine.split(",", -1);
            Map<String, Integer> columnMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                columnMap.put(headers[i].trim().toLowerCase(), i);
            }

            if (!columnMap.containsKey("date") || !columnMap.containsKey("close")) {
                throw new RuntimeException("CSV must contain 'Date' and 'Close' columns.");
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] fields = line.split(",", -1);

                try {
                    String dateStr = fields[columnMap.get("date")].trim();
                    String closeStr = fields[columnMap.get("close")].trim();

                    if (dateStr.isEmpty() || closeStr.isEmpty()) continue;

                    if (!closeStr.matches("-?\\d+(\\.\\d+)?")) continue;

                    LocalDate date = LocalDate.parse(dateStr); // s obzirom da je format datuma (yyyy-MM-dd)
                    BigDecimal closePrice = new BigDecimal(closeStr);

                    Stock stock = new Stock();
                    stock.setCompany(company);
                    stock.setDate(date);
                    stock.setClosePrice(closePrice);

                    stocksList.add(stock);
                } catch (Exception ex) {
                    continue;
                }
            }

            if (!stocksList.isEmpty()) {
                stockRepository.saveAll(stocksList);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file.", e);
        }
    }


}






