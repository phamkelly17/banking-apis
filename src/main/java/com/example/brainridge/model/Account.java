package com.example.brainridge.model;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private UUID id;
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public Account(double initialBalance, String name){
        this.name = name;
        this.id = UUID.randomUUID();
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }
}
