package com.example.transaction_service.service;

import com.example.transaction_service.exception.TransactionNotFoundException;
import com.example.transaction_service.model.Transaction;
import com.example.transaction_service.repository.TransactionRepository;
import com.example.transaction_service.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testGetAllTransactions() {
        // given
        Transaction transaction1 = new Transaction();
        transaction1.setType("type1");
        transaction1.setActor("actor1");

        Transaction transaction2 = new Transaction();
        transaction2.setType("type2");
        transaction2.setActor("actor2");

        when(transactionRepository.findAll()).thenReturn(List.of(transaction1, transaction2));

        // when
        List<Transaction> transactions = transactionService.getAllTransactions();

        // then
        assertThat(transactions).hasSize(2);
        assertThat(transactions).extracting(Transaction::getType)
                .containsExactlyInAnyOrder("type1", "type2");
    }

    @Test
    void testGetTransactionById() {
        // given
        Transaction transaction = new Transaction();
        transaction.setType("type1");
        transaction.setActor("actor1");

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        // when
        Transaction result = transactionService.getTransactionById(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("type1");
        assertThat(result.getActor()).isEqualTo("actor1");
    }

    @Test
    void testCreateTransaction() {
        // given
        Transaction transaction = new Transaction();
        transaction.setType("type1");
        transaction.setActor("actor1");

        Map<String, String> transactionDetails = new HashMap<>();
        transactionDetails.put("key", "value");
        transaction.setTransactionDetails(transactionDetails);

        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction t = invocation.getArgument(0);
            t.setId(1L);
            return t;
        });

        // when
        Transaction result = transactionService.createTransaction(transaction);

        // then
        assertNotNull(result.getId());
        assertThat(result.getType()).isEqualTo("type1");
        assertThat(result.getActor()).isEqualTo("actor1");
    }

    @Test
    void testCreateTransactionWithEmptyDetailsShouldFail() {
        // given
        Transaction transaction = new Transaction();
        transaction.setType("type1");
        transaction.setActor("actor1");
        transaction.setTransactionDetails(new HashMap<>());

        // when + then
        assertThrows(IllegalArgumentException.class, () -> transactionService.createTransaction(transaction));
    }

    @Test
    void testUpdateTransaction() {
        // given
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setType("type1");
        transaction.setActor("actor1");

        // when
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        transaction.setType("type2");
        Transaction updatedTransaction = transactionService.updateTransaction(transaction);

        // then
        assertThat(updatedTransaction.getType()).isEqualTo("type2");
    }

    @Test
    void testUpdateTransactionNotFound() {
        // given
        Transaction transaction = new Transaction();
        transaction.setId(1L);

        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        // when + then
        assertThrows(TransactionNotFoundException.class, () -> transactionService.updateTransaction(transaction));
    }


    @Test
    void testDeleteTransaction() {
        // given
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setType("type1");
        transaction.setActor("actor1");

        // when
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        transactionService.deleteTransaction(1L);

        // then
        verify(transactionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTransactionNotFound() {
        // given
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        // when + then
        assertThrows(TransactionNotFoundException.class, () -> transactionService.deleteTransaction(1L));
    }

    @Test
    void testSearchByType() {
        // given
        Transaction transaction1 = new Transaction();
        transaction1.setType("type1");
        transaction1.setActor("actor1");

        when(transactionRepository.findByType("type1")).thenReturn(List.of(transaction1));

        // when
        List<Transaction> transactions = transactionService.searchByType("type1");

        // then
        assertThat(transactions).hasSize(1);
        assertThat(transactions.getFirst().getType()).isEqualTo("type1");
    }

    @Test
    void testSearchByActor() {
        // given
        Transaction transaction1 = new Transaction();
        transaction1.setType("type1");
        transaction1.setActor("actor1");

        when(transactionRepository.findByActor("actor1")).thenReturn(List.of(transaction1));

        // when
        List<Transaction> transactions = transactionService.searchByActor("actor1");

        // then
        assertThat(transactions).hasSize(1);
        assertThat(transactions.getFirst().getActor()).isEqualTo("actor1");
    }

    @Test
    void testSearchByTransactionDetailKeyAndValue() {
        // given
        Transaction transaction = new Transaction();
        transaction.setType("type1");
        transaction.setActor("actor1");

        Map<String, String> transactionDetails = new HashMap<>();
        transactionDetails.put("key1", "value1");
        transactionDetails.put("key2", "value2");

        transaction.setTransactionDetails(transactionDetails);

        when(transactionRepository.searchByTransactionDetailKeyAndValue("key1", "value1")).thenReturn(List.of(transaction));

        // when
        List<Transaction> transactions = transactionService.searchByTransactionDetailKeyAndValue("key1", "value1");

        // then
        assertThat(transactions).hasSize(1);
        assertThat(transactions.getFirst()).isEqualTo(transaction);
    }
}


