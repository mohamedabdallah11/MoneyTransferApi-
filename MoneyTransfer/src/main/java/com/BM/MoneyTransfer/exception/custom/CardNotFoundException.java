package com.BM.MoneyTransfer.exception.custom;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
