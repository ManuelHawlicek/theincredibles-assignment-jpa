package com.accenture.theincrediblesassignmentjpa.models.repositories;

import com.accenture.theincrediblesassignmentjpa.models.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "ALTER TABLE company AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    List<Company> findByNameContaining(String name);

    Optional<Company> findFirstByName(String companyName);

    boolean existsByName(String name);
}