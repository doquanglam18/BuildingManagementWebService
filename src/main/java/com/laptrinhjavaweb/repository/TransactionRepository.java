package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    @Query(value = "SELECT * FROM transactions t WHERE t.customerid = :customerId", nativeQuery = true)
    List<TransactionEntity> findByCustomerId(Long customerId);
}
