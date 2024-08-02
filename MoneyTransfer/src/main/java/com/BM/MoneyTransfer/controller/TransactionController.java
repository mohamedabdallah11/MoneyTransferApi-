package com.BM.MoneyTransfer.controller;

import com.BM.MoneyTransfer.response.TransactionResponse;
import com.BM.MoneyTransfer.service.TransactionService;
import com.BM.MoneyTransfer.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public Page<Transaction> getTransactionHistory(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        return transactionService.findByEmail(userEmail, pageable);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody Transaction transaction) {
        // Get the authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        transaction.setSenderEmail(userEmail);

        Transaction savedTransaction = transactionService.save(transaction);

        return ResponseEntity.ok(new TransactionResponse(savedTransaction, "Transaction approved"));
    }
}
