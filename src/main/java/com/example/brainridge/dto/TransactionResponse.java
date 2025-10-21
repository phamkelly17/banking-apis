package com.example.brainridge.dto;

import com.example.brainridge.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class TransactionResponse {
    @NotNull
    private TransactionType transactionType; // enum of WITHDRAWL or DEPOSIT
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private double amount;
    @NotNull
    private double newBalance;
}
