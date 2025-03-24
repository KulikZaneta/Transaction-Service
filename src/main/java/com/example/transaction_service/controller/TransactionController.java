package com.example.transaction_service.controller;

import com.example.transaction_service.mapper.TransactionMapper;
import com.example.transaction_service.model.Transaction;
import com.example.transaction_service.model.dto.TransactionDto;
import com.example.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final TransactionMapper transactionMapper;

    @GetMapping("/all-transactions")
    public List<TransactionDto> getAllTransactions() {
        return transactionMapper.mapToTransactionDtoList(transactionService.getAllTransactions());
    }

    @GetMapping("/transaction/{id}")
    public TransactionDto getTransactionById(@PathVariable Long id) {
        return transactionMapper.mapToTransactionDto(transactionService.getTransactionById(id));
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto addTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction newTransaction = transactionMapper.mapToTransaction(transactionDto);
        return transactionMapper.mapToTransactionDto(transactionService.createTransaction(newTransaction));
    }

    @PutMapping("/transaction/{id}")
    public TransactionDto updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        Transaction updatedTransaction = transactionMapper.mapToTransaction(transactionDto);
        updatedTransaction.setId(id);
        updatedTransaction.setTimestamp(LocalDateTime.now());
        return transactionMapper.mapToTransactionDto(transactionService.updateTransaction(updatedTransaction));
    }

    @DeleteMapping("/transaction/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/search/by-type")
    public List<TransactionDto> searchTransactionsByType(@RequestParam String type) {
        return transactionMapper.mapToTransactionDtoList(transactionService.searchByType(type));
    }

    @GetMapping("/search/by-actor")
    public List<TransactionDto> searchTransactionsByActor(@RequestParam String actor) {
        return transactionMapper.mapToTransactionDtoList(transactionService.searchByActor(actor));
    }

    @GetMapping("/search-by-transaction-detail")
    public List<TransactionDto> searchByTransactionDetailKeyAndValue(
            @RequestParam("key") String key,
            @RequestParam("value") String value) {

        List<Transaction> transactions = transactionService.searchByTransactionDetailKeyAndValue(key, value);

        return transactions.stream()
                .map(transactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }
}

