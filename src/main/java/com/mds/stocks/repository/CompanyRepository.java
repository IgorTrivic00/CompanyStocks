package com.mds.stocks.repository;
import org.springframework.stereotype.Repository;
import com.mds.stocks.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findBySymbol(String symbol);
}
