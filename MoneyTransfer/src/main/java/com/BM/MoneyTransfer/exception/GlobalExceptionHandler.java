package com.BM.MoneyTransfer.exception;

import com.BM.MoneyTransfer.exception.custom.InsufficientFundsException;
import com.BM.MoneyTransfer.exception.custom.RecipientNotFoundException;
import com.BM.MoneyTransfer.exception.custom.SenderNotFoundException;
import com.BM.MoneyTransfer.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SenderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSenderNotFound(SenderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Sender Not Found", ex.getMessage()));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Insufficient Funds", ex.getMessage()));
    }

    @ExceptionHandler(RecipientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecipientNotFound(RecipientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Recipient Not Found", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server Error", ex.getMessage()));
    }
}
