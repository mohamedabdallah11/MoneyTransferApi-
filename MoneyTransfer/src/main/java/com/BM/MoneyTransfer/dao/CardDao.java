package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardDao extends JpaRepository<Card, String> {
    @Query(value = "SELECT balance from card where card_number = :card_no for update", nativeQuery = true)
    Double findCardBalanceByCardNumberForUpdate(@Param("card_no") String cardNumber);

}
