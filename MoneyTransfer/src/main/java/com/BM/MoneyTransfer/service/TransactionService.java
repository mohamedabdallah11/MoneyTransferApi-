package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.entity.Transaction;
import com.BM.MoneyTransfer.exception.custom.InsufficientFundsException;
import com.BM.MoneyTransfer.exception.custom.RecipientNotFoundException;
import com.BM.MoneyTransfer.exception.custom.SenderNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TransactionService {

    // Save or update a transaction
    Transaction save(Transaction transaction) throws InsufficientFundsException, SenderNotFoundException, RecipientNotFoundException;

    // Find a transaction by its ID
    Optional<Transaction> findById(Long id);

    // Get all transactions with pagination
    Page<Transaction> findByEmail(String email, Pageable pageable);
}