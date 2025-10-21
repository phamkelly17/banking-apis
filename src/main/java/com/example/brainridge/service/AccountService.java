package com.example.brainridge.service;

import com.example.brainridge.dto.*;
import com.example.brainridge.model.Account;
import com.example.brainridge.model.Transaction;
import com.example.brainridge.model.TransactionType;
import com.example.brainridge.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService (AccountRepository repository){
        this.repository = repository;
    }

    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getBalance()
        );
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionType(),
                repository.getAccountById(transaction.getAccountFromId()).getName(),
                repository.getAccountById(transaction.getAccountToId()).getName(),
                transaction.getAmount(),
                transaction.getNewBalance()
        );
    }

    public AccountResponse addAccount(AccountRequest request){
            Account account = new Account(request.getBalance(), request.getName());
            repository.addAccount(account);
            return toAccountResponse(account);
    }

    public TransactionResponse transfer(TransactionRequest request){
        Account accountFrom = repository.getAccountById(request.getAccountFromId());
        Account accountTo = repository.getAccountById(request.getAccountToId());
        double transferAmount = request.getAmount();
        double accountFromNewBalance = accountFrom.getBalance() - transferAmount;

        if (accountFromNewBalance < 0){
            throw new IllegalArgumentException("Insufficient funds.");
        }
        accountFrom.setBalance(accountFromNewBalance);
        accountTo.setBalance(accountTo.getBalance() + request.getAmount());

        Transaction transactionFrom = new Transaction(accountFrom, accountTo, TransactionType.WITHDRAWL, request.getAmount(), accountFrom.getBalance());
        accountFrom.getTransactions().add(transactionFrom);

        Transaction transactionTo = new Transaction(accountFrom, accountTo, TransactionType.DEPOSIT, request.getAmount(), accountTo.getBalance());
        accountTo.getTransactions().add(transactionTo);

        return toTransactionResponse(transactionFrom);

    }

    public List<TransactionResponse> getTransactionHistory(TransactionHistoryRequest request){
        List<Transaction> transactions = repository.getTransactionHistory(request.getAccountId());
        int page = request.getPage();
        int pageSize = request.getPageSize();
        int startIndex = (page - 1) * pageSize;
        if (startIndex > transactions.size()) startIndex = 0;
        int endIndex = startIndex + pageSize;
        if (endIndex > transactions.size()){
            endIndex = transactions.size();
        }
        List<TransactionResponse> res = new ArrayList<>();
        for (Transaction transaction : transactions.subList(startIndex, endIndex)) {
            res.add(toTransactionResponse(transaction));
        }

        return res;
    }
}
