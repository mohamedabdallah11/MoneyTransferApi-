package com.BM.MoneyTransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    private String cardNumber;
    private String cardholderName;
    private String expirationDate;
    private String cvv;
    private String pin;
    private Double balance;
    private String currency;
    private String accountType;
}
