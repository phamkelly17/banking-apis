package com.example.brainridge.model;
import java.util.UUID;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Transaction {
    private UUID id;
    private TransactionType transactionType;
    private UUID accountFromId;
    private UUID accountToId;
    private LocalDateTime datetime;
    private double amount;
    private double newBalance;

    public Transaction(Account accountFrom, UUID accountToId, TransactionType transactionType, double amount, double balance){
        this.id = UUID.randomUUID();
        this.transactionType = transactionType;
        this.accountFromId = accountFrom.getId();
        this.accountToId = accountToId;
        this.datetime = LocalDateTime.now();
        this.amount = amount;
        this.newBalance = balance;
    }
}
