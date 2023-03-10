package com.accenture.theincrediblesassignmentjpa.models.repositories;

import com.accenture.theincrediblesassignmentjpa.models.Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "ALTER TABLE stock AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    List<Stock> findAllByCompanyName(String name);

    List<Stock> findAllByCompanyId(long companyId);
}