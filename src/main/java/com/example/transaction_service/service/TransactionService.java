package com.example.transaction_service.service;

import com.example.transaction_service.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    Transaction getTransactionById(Long id);

    Transaction createTransaction(Transaction transaction);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);

    List<Transaction> searchByType(String type);

    List<Transaction> searchByActor(String actor);

    List<Transaction> searchByTransactionDetailKeyAndValue(String key, String value);

}

