package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDao extends JpaRepository<Card, String> {
}
