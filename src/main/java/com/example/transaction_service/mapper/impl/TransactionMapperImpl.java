package com.example.transaction_service.mapper.impl;

import com.example.transaction_service.mapper.TransactionMapper;
import com.example.transaction_service.model.Transaction;
import com.example.transaction_service.model.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction mapToTransaction(TransactionDto transactionDto) {
        if (transactionDto == null) {
            throw new IllegalArgumentException("Transaction is null");
        }
        return Transaction.builder()
                .type(transactionDto.getType())
                .actor(transactionDto.getActor())
                .transactionDetails(transactionDto.getTransactionDetails())
                .build();
    }

    @Override
    public TransactionDto mapToTransactionDto(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction is null");
        }
        return TransactionDto.builder()
                .type(transaction.getType())
                .actor(transaction.getActor())
                .transactionDetails(transaction.getTransactionDetails())
                .build();
    }

    @Override
    public List<TransactionDto> mapToTransactionDtoList(List<Transaction> transactionList) {
        if (transactionList == null || transactionList.isEmpty()) {
            throw new IllegalArgumentException("Transaction list is empty or null");
        }
        return transactionList.stream()
                .map(this::mapToTransactionDto)
                .collect(Collectors.toList());
    }
}

