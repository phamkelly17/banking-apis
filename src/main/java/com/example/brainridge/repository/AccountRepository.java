package com.example.brainridge.repository;

import com.example.brainridge.model.Account;
import com.example.brainridge.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class AccountRepository {
    private final Map<UUID, Account> accounts = new HashMap<>();

    public Account getAccountById(UUID accountId) {
        Account account = accounts.get(accountId);
        if (account == null){
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        return account;
    }

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    public List<Transaction> getTransactionHistory(UUID accountId) {
        Account account = getAccountById(accountId);
        return account.getTransactions();
    }
}
