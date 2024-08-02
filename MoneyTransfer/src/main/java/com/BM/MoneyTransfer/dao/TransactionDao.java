package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.senderEmail = :email OR t.recipientEmail = :email ORDER BY t.date DESC")
    Page<Transaction> findByEmail(@Param("email") String email, Pageable pageable);

}
