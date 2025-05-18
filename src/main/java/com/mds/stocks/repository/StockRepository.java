package com.mds.stocks.repository;
import org.springframework.stereotype.Repository;
import com.mds.stocks.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    List<Stock> findAllByCompanyId(Long companyId);

    @Query("SELECT s FROM Stock s WHERE s.company.symbol = :symbol AND s.date BETWEEN :startDate AND :endDate")
    List<Stock> findStocksTimeIntervalByCompanySymbol(
            @Param("symbol") String symbol,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
