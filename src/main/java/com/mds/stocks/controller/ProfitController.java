package com.mds.stocks.controller;
import com.mds.stocks.dto.ProfitRequest;
import com.mds.stocks.dto.ProfitResultResponse;
import com.mds.stocks.service.ProfitService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class ProfitController {

    private final ProfitService profitService;

    public ProfitController(ProfitService profitService) {
        this.profitService = profitService;
    }


    @GetMapping("/profit")
    public ProfitResultResponse calculateProfit(@RequestBody ProfitRequest profitRequest) {
        return profitService.calculateProfit(profitRequest.symbol(), profitRequest.fromDate(), profitRequest.toDate());
    }
}