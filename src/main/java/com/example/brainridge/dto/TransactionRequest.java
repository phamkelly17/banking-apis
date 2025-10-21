package com.example.brainridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.UUID;

@AllArgsConstructor
@Data
public class TransactionRequest {
    @NotNull
    private UUID accountFromId;
    @NotNull
    private UUID accountToId;
    @NotNull @Positive
    private double amount;
}
