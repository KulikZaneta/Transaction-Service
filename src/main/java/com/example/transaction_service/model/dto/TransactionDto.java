package com.example.transaction_service.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionDto {

    @NotNull(message = "Transaction type is required")
    private String type;

    @NotNull(message = "Actor is required")
    private String actor;

    private Map<String, String> transactionDetails;
}

