package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.dao.TransactionDao;
import com.BM.MoneyTransfer.entity.Card;
import com.BM.MoneyTransfer.entity.Transaction;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    TransactionDao transactionDao;
    CardService cardService;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        validateTransaction(transaction);

        performTransaction(transaction);
        return this.transactionDao.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return this.transactionDao.findById(id);
    }

    @Override
    public Page<Transaction> findByEmail(String email, Pageable pageable) {
        return this.transactionDao.findByEmail(email, pageable);
    }

    protected void validateTransaction(Transaction transaction)
    {
        Card senderCard = cardService.getCard(transaction.getSenderCardNumber());
        if (senderCard == null) {
            throw new IllegalArgumentException("Sender not found"); // change this to a custom exception
        }
        if (transaction.getAmount().compareTo(senderCard.getBalance()) > 0) {
            throw new IllegalArgumentException("Insufficient funds"); // change this to a custom exception
        }
        Card recipientCard = cardService.getCard(transaction.getRecipientCardNumber());
        if (recipientCard == null) {
            throw new IllegalArgumentException("Recipient not found"); // change this to a custom exception
        }
    }
    protected void performTransaction(Transaction transaction)
    {
        Card senderCard = cardService.getCard(transaction.getSenderCardNumber());
        Card recipientCard = cardService.getCard(transaction.getRecipientCardNumber());

        cardService.updateCardBalance(senderCard.getCardNumber(), senderCard.getBalance() - transaction.getAmount());
        cardService.updateCardBalance(recipientCard.getCardNumber(),recipientCard.getBalance() + transaction.getAmount());
    }

}
