package com.example.brainridge.service;

import com.example.brainridge.dto.AccountRequest;
import com.example.brainridge.dto.AccountResponse;
import com.example.brainridge.dto.TransactionRequest;
import com.example.brainridge.dto.TransactionResponse;
import com.example.brainridge.model.Account;
import com.example.brainridge.model.TransactionType;
import com.example.brainridge.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Mock
    private AccountRepository repository;
    private AccountService service;

    private AccountRequest accountReq1;
    private AccountRequest accountReq2;

    private AccountResponse accountRes1;
    private AccountResponse accountRes2;

    @BeforeEach
    void setUp() {
        repository = new AccountRepository();
        service = new AccountService(repository);

        accountReq1 = new AccountRequest(1000, "Tester 1");
        accountReq2 = new AccountRequest(500, "Tester 2");

        accountRes1 = service.addAccount(accountReq1);
        accountRes2 = service.addAccount(accountReq2);
    }

    @Test
    void testAddAccount_Success() {
        Account found = repository.getAccountById(accountRes1.getId());
        assertEquals("Tester 1", found.getName());
        assertEquals(1000, found.getBalance());
    }

    @Test
    void testTransfer_Success() {
        TransactionRequest request = new TransactionRequest(accountRes1.getId(), accountRes2.getId(), 200);
        TransactionResponse result = service.transfer(request);

        assertEquals(result.getTo(), accountRes2.getName());
        assertEquals(result.getFrom(), accountRes1.getName());
        assertEquals(TransactionType.WITHDRAWL, result.getTransactionType());
        assertEquals(200, result.getAmount());
        assertEquals(800, result.getNewBalance());

        assertEquals(800, repository.getAccountById(accountRes1.getId()).getBalance());
        assertEquals(700, repository.getAccountById(accountRes2.getId()).getBalance());
    }

    @Test
    void testTransfer_InsufficientFunds() {
        TransactionRequest request = new TransactionRequest(accountRes1.getId(), accountRes2.getId(), 2000);

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> service.transfer(request)
        );

        assertEquals("Insufficient funds.", e.getMessage());
    }

    @Test
    void testTransfer_InvalidAccount() {
        UUID random = UUID.randomUUID();
        TransactionRequest request = new TransactionRequest(accountRes1.getId(), random, 200);

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> service.transfer(request)
        );

        assertEquals("Account not found: " + random.toString(), e.getMessage());
    }

}