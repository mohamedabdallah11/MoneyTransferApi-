package com.BM.MoneyTransfer.exception.custom;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
