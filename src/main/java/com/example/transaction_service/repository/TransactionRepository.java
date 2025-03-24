package com.example.transaction_service.repository;

import com.example.transaction_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(String type);

    List<Transaction> findByActor(String actor);

    @Query(value = "SELECT t.* FROM transaction t " +
            "JOIN transaction_metadata td ON t.id = td.transaction_id " +
            "WHERE td.transaction_key = :key AND td.value = :value",
            nativeQuery = true)
    List<Transaction> searchByTransactionDetailKeyAndValue(@Param("key") String key, @Param("value") String value);
}


