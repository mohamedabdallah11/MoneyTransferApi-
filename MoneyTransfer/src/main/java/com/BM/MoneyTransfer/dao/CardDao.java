package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.Card;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface CardDao extends JpaRepository<Card, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Card c WHERE c.cardNumber = :cardNumber")
    Card findCardByCardNumberForUpdate(String cardNumber);

}
