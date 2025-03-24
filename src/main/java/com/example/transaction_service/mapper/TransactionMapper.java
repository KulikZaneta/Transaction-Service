package com.example.transaction_service.mapper;

import com.example.transaction_service.model.Transaction;
import com.example.transaction_service.model.dto.TransactionDto;

import java.util.List;

public interface TransactionMapper {

    Transaction mapToTransaction(TransactionDto transactionDto);

    TransactionDto mapToTransactionDto(Transaction transaction);

    List<TransactionDto> mapToTransactionDtoList(List<Transaction> transactionList);
}
