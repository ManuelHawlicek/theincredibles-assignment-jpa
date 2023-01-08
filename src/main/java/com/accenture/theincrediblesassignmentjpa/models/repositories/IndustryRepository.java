package com.accenture.theincrediblesassignmentjpa.models.repositories;

import com.accenture.theincrediblesassignmentjpa.models.Industry;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "ALTER TABLE industry AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    Optional<Industry> findFirstByName(String industryName);
}