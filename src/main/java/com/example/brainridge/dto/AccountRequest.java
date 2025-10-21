package com.example.brainridge.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@AllArgsConstructor
@Data
public class AccountRequest {
    @NotNull @Positive
    private double balance;
    @NotNull
    private String name;

}
