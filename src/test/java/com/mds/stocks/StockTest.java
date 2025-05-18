package com.mds.stocks;


import com.mds.stocks.dto.ProfitResultResponse;
import com.mds.stocks.service.ProfitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfitServiceIntegrationTest {

    @Autowired
    private ProfitService profitService;

    //@Test
    void ProfitTest() {
        String symbol = "EPL";
        LocalDate fromDate = LocalDate.of(2018, 4, 01);
        LocalDate toDate = LocalDate.of(2018, 4, 10);

        ProfitResultResponse result = profitService.calculateProfit(symbol, fromDate, toDate);

        assertNotNull(result);
        assertNotNull(result.currentPeriod());
        assertNotNull(result.previousPeriod());
        assertNotNull(result.nextPeriod());
        System.out.println(result.currentPeriod());
        System.out.println(result.previousPeriod());
        System.out.println(result.nextPeriod());
        System.out.println("The biggest profit: " + result.currentPeriod().profit());
        System.out.println("Multi-trade profit: " + result.totalProfit());
    }
}
