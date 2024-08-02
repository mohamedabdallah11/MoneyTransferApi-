package com.BM.MoneyTransfer.exception.custom;

public class InvalidCardDetailsException extends RuntimeException {
    public InvalidCardDetailsException(String message) {
        super(message);
    }
}
