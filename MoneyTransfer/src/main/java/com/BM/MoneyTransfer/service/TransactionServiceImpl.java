package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.dao.CardDao;
import com.BM.MoneyTransfer.dao.TransactionDao;
import com.BM.MoneyTransfer.dto.enums.Status;
import com.BM.MoneyTransfer.entity.Card;
import com.BM.MoneyTransfer.entity.Transaction;
import com.BM.MoneyTransfer.exception.custom.InsufficientFundsException;
import com.BM.MoneyTransfer.exception.custom.RecipientNotFoundException;
import com.BM.MoneyTransfer.exception.custom.SenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    TransactionDao transactionDao;
    CardDao cardDao;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao, CardDao carddao) {
        this.transactionDao = transactionDao;
        this.cardDao = carddao;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction save(Transaction transaction) throws SenderNotFoundException, RecipientNotFoundException, InsufficientFundsException {
        Card senderCard = cardDao.findCardByCardNumberForUpdate(transaction.getSenderCardNumber());
        Card recipientCard = cardDao.findById(transaction.getRecipientCardNumber()).orElse(null);

        if (senderCard == null || !Objects.equals(senderCard.getUser().getEmail(), transaction.getSenderEmail())) {
            transaction.setStatus(Status.DENIED);
            throw new SenderNotFoundException("Sender Not Found");
        }
        if (recipientCard == null || !Objects.equals(recipientCard.getUser().getEmail(), transaction.getRecipientEmail())) {
            transaction.setStatus(Status.DENIED);
            throw new RecipientNotFoundException("Recipient Not Found");
        }
        if (transaction.getAmount().compareTo(senderCard.getBalance()) > 0) {
            transaction.setStatus(Status.DENIED);
            transactionDao.save(transaction);
            throw new InsufficientFundsException("Insufficient funds");
        } else {
            transaction.setStatus(Status.APPROVED);
            senderCard.setBalance(senderCard.getBalance() - transaction.getAmount());
            recipientCard.setBalance(recipientCard.getBalance() + transaction.getAmount());
        }
        return transactionDao.save(transaction);
    }


    @Override
    public Optional<Transaction> findById(Long id) {
        return this.transactionDao.findById(id);
    }

    @Override
    public Page<Transaction> findByEmail(String email, Pageable pageable) {
        return this.transactionDao.findByEmail(email, pageable);
    }


}
