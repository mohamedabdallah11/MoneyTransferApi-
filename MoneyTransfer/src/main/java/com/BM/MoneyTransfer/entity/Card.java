package com.BM.MoneyTransfer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "cardholder_name")
    private String cardholderName;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "pin")
    private String pin;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "currency")
    private String currency;


    @Column(name = "account_type")
    private String accountType;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_email")
    private User user;

    public Card(String cardNumber, String cardholderName, String expirationDate, String cvv, String pin, Double balance, String currency, String accountType) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.pin = pin;
        this.balance = balance;
        this.currency = currency;
        this.accountType = accountType;
    }
}
