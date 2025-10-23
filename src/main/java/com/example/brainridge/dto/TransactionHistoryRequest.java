package com.example.brainridge.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class TransactionHistoryRequest {
    @NotNull
    private UUID accountId;
    private int page = 1;
    private int pageSize = 5;
}
