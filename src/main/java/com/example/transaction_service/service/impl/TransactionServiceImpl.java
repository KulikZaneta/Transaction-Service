package com.example.transaction_service.service.impl;

import com.example.transaction_service.exception.TransactionNotFoundException;
import com.example.transaction_service.model.Transaction;
import com.example.transaction_service.repository.TransactionRepository;
import com.example.transaction_service.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        validateTransactionDetails(transaction.getTransactionDetails());
        return transactionRepository.save(transaction);
    }

    private void validateTransactionDetails(Map<String, String> transactionDetailsMap) {
        if (transactionDetailsMap == null || transactionDetailsMap.isEmpty()) {
            throw new IllegalArgumentException("Transaction details map can not be null or empty.");
        }

        transactionDetailsMap.forEach((key, value) -> {
            if (key == null || key.trim().isEmpty()) {
                throw new IllegalArgumentException("Transaction details map can not have empty or null keys.");
            }
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException("Transaction details map can not have empty or null values.");
            }
        });
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        if (!transactionRepository.existsById(transaction.getId())) {
            throw new TransactionNotFoundException("Transaction with id " + transaction.getId() + " not found");
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new TransactionNotFoundException("Transaction with id " + id + " not found");
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> searchByType(String type) {
        return transactionRepository.findByType(type);
    }

    @Override
    public List<Transaction> searchByActor(String actor) {
        return transactionRepository.findByActor(actor);
    }

    @Override
    public List<Transaction> searchByTransactionDetailKeyAndValue(String key, String value) {
        return transactionRepository.searchByTransactionDetailKeyAndValue(key, value);
    }
}

