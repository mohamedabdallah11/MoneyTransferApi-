package com.BM.MoneyTransfer.controller;

import com.BM.MoneyTransfer.dto.enums.Status;
import com.BM.MoneyTransfer.entity.Transaction;
import com.BM.MoneyTransfer.exception.custom.InsufficientFundsException;
import com.BM.MoneyTransfer.exception.custom.RecipientNotFoundException;
import com.BM.MoneyTransfer.exception.custom.SenderNotFoundException;
import com.BM.MoneyTransfer.response.TransactionResponse;
import com.BM.MoneyTransfer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public Page<Transaction> getTransactionHistory(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();


        return transactionService.findByEmail(userEmail, pageable);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody Transaction transaction) throws InsufficientFundsException, RecipientNotFoundException, SenderNotFoundException {
        // Get the authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        transaction.setSenderEmail(userEmail);

        try {

            transaction = transactionService.save(transaction);
        } catch (RecipientNotFoundException | SenderNotFoundException | InsufficientFundsException e) {
            throw e;
        }

        String message;
        if (transaction.getStatus() == Status.APPROVED) {
            message = "Transaction approved";
        } else {
            message = "Transaction failed";
        }

        return ResponseEntity.ok(new TransactionResponse(transaction, message));
    }
}
