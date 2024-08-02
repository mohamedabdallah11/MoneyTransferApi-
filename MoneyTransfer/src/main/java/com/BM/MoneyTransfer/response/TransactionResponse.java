package com.BM.MoneyTransfer.response;


import com.BM.MoneyTransfer.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    Transaction transaction;
    String message;
}
