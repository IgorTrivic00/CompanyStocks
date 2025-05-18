package com.mds.stocks.controller;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

import com.mds.stocks.service.ImportStockService;
@RestController
@RequestMapping("/api/stocks")
public class StockImportController {

    private final ImportStockService importService;

    public StockImportController(ImportStockService importService) {
        this.importService = importService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCsv(
            @RequestParam("file") MultipartFile file,
            @RequestParam("symbol") String symbol){
            importService.importCsv(file, symbol);
        return ResponseEntity.ok("Successfully imported data for " + symbol);
    }
}