package com.example.brainridge.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class AccountResponse {
    @NotNull
    private UUID id; // usually wouldn't expose this to client, but we need to make more requests
    @NotNull
    private String name;
    @NotNull
    private double balance;
}
