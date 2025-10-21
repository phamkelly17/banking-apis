package com.example.brainridge.controller;

import com.example.brainridge.dto.*;
import com.example.brainridge.model.Transaction;
import com.example.brainridge.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping ("/addAccount")
    public ResponseEntity<AccountResponse> addAccount(@Valid @RequestBody AccountRequest request){
        AccountResponse res = service.addAccount(request);
            return ResponseEntity.ok(res);
    }

    @PutMapping ("/transfer")
    public ResponseEntity<?> transferFunds(@Valid @RequestBody TransactionRequest request){
        TransactionResponse res = service.transfer(request);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getTransactionHistory")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(@Valid @RequestBody TransactionHistoryRequest request){
        return ResponseEntity.ok(service.getTransactionHistory(request));
    }
}
