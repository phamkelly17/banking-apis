package com.example.brainridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class TransactionHistoryRequest {
    @NotNull
    private UUID accountId;
    private int page = 1;
    private int pageSize = 5;
}
