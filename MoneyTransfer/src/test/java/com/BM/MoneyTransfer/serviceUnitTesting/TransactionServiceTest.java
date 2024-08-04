package com.BM.MoneyTransfer.serviceUnitTesting;
import com.BM.MoneyTransfer.dao.CardDao;
import com.BM.MoneyTransfer.dto.CardDTO;
import com.BM.MoneyTransfer.service.TransactionServiceImpl;
import com.BM.MoneyTransfer.service.CardService;
import com.BM.MoneyTransfer.dao.TransactionDao;
import com.BM.MoneyTransfer.dto.enums.Status;
import com.BM.MoneyTransfer.entity.Card;
import com.BM.MoneyTransfer.entity.Transaction;
import com.BM.MoneyTransfer.exception.custom.InsufficientFundsException;
import com.BM.MoneyTransfer.exception.custom.RecipientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @Mock
    private CardDao cardDao;

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private CardService cardService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTransaction_Success() throws Exception {
        Transaction transaction = new Transaction(
                "1234567890123456", "6543210987654321",
                "senderUser", "recipientUser",
                "sender@example.com", "recipient@example.com",
                100.0, new Date(), Status.PENDING);

        Card senderCard = new Card("1234567890123456", 200.0);
        Card recipientCard = new Card("6543210987654321", 50.0);

        when(cardDao.findCardBalanceByCardNumberForUpdate(anyString())).thenReturn(200.0);
        when(cardService.getCard(anyString())).thenReturn(senderCard).thenReturn(recipientCard);
        when(transactionDao.save(any(Transaction.class))).thenReturn(transaction);

        Transaction savedTransaction = transactionService.save(transaction);

        assertEquals(Status.APPROVED, savedTransaction.getStatus());
        verify(cardDao).findCardBalanceByCardNumberForUpdate(anyString());
        verify(cardService).updateCardBalance(anyString(), anyDouble());
        verify(transactionDao).save(any(Transaction.class));
    }

    @Test
    public void testSaveTransaction_InsufficientFunds() throws Exception {
        Transaction transaction = new Transaction(
                "1234567890123456", "6543210987654321",
                "senderUser", "recipientUser",
                "sender@example.com", "recipient@example.com",
                300.0, new Date(), Status.PENDING);

        when(cardDao.findCardBalanceByCardNumberForUpdate(anyString())).thenReturn(200.0);

        Transaction savedTransaction = transactionService.save(transaction);

        assertEquals(Status.DENIED, savedTransaction.getStatus());
        verify(transactionDao).save(any(Transaction.class));
    }


}
