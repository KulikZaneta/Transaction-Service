package com.example.transaction_service.mapper;

import com.example.transaction_service.mapper.impl.TransactionMapperImpl;
import com.example.transaction_service.model.Transaction;
import com.example.transaction_service.model.dto.TransactionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionMapperTest {

    private TransactionMapperImpl transactionMapper;

    @BeforeEach
    public void setup() {
        transactionMapper = new TransactionMapperImpl();
    }

    @Test
    public void testMapToTransaction() {
        // given
        TransactionDto transactionDto = TransactionDto.builder()
                .type("type")
                .actor("actor")
                .transactionDetails(Map.of("key", "value"))
                .build();

        // when
        Transaction transaction = transactionMapper.mapToTransaction(transactionDto);

        // then
        assertEquals(transactionDto.getType(), transaction.getType());
        assertEquals(transactionDto.getActor(), transaction.getActor());
        assertEquals(transactionDto.getTransactionDetails(), transaction.getTransactionDetails());
    }

    @Test
    public void testMapToTransactionDto() {
        // given
        Transaction transaction = Transaction.builder()
                .type("type")
                .actor("actor")
                .transactionDetails(Map.of("key", "value"))
                .build();

        // when
        TransactionDto transactionDto = transactionMapper.mapToTransactionDto(transaction);

        // then
        assertEquals(transaction.getType(), transactionDto.getType());
        assertEquals(transaction.getActor(), transactionDto.getActor());
        assertEquals(transaction.getTransactionDetails(), transactionDto.getTransactionDetails());
    }

    @Test
    public void testMapToTransactionDtoList() {
        // given
        List<Transaction> transactionList = List.of(
                Transaction.builder()
                        .type("type1")
                        .actor("actor1")
                        .transactionDetails(Map.of("key1", "value1"))
                        .build(),
                Transaction.builder()
                        .type("type2")
                        .actor("actor2")
                        .transactionDetails(Map.of("key2", "value2"))
                        .build()
        );

        // when
        List<TransactionDto> transactionDtoList = transactionMapper.mapToTransactionDtoList(transactionList);

        // then
        assertEquals(2, transactionDtoList.size());
        assertEquals(transactionList.getFirst().getType(), transactionDtoList.getFirst().getType());
        assertEquals(transactionList.get(0).getActor(), transactionDtoList.get(0).getActor());
        assertEquals(transactionList.get(0).getTransactionDetails(), transactionDtoList.get(0).getTransactionDetails());
        assertEquals(transactionList.get(1).getType(), transactionDtoList.get(1).getType());
        assertEquals(transactionList.get(1).getActor(), transactionDtoList.get(1).getActor());
        assertEquals(transactionList.get(1).getTransactionDetails(), transactionDtoList.get(1).getTransactionDetails());
    }

    @Test
    public void testMapToTransactionDtoListEmpty() {
        // given
        List<Transaction> transactionList = List.of();

        // when
        assertThrows(IllegalArgumentException.class, () -> transactionMapper.mapToTransactionDtoList(transactionList));
    }

    @Test
    public void testMapToTransactionDtoListNull() {
        // given
        List<Transaction> transactionList = null;

        // when
        assertThrows(IllegalArgumentException.class, () -> transactionMapper.mapToTransactionDtoList(null));
    }
}

