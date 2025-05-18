package com.mds.stocks.repository;
import org.springframework.stereotype.Repository;
import com.mds.stocks.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    List<Stock> findAllByCompanyId(Long companyId);


}
